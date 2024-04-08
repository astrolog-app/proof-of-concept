package ui.popUps.rowEditors;

import models.imagingFrames.*;
import models.equipment.Equipment;
import models.equipment.EquipmentType;
import models.settings.AppConfig;
import models.settings.TempType;
import models.tableModels.LibraryTableModel;
import services.fileHandler.ImagingFrameStore;
import ui.customComponents.CustomComboBox;
import ui.customComponents.CustomFileChooser;
import utils.Temp;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class LibraryRowEditor extends JDialog {
    private final boolean edit;
    private final CalibrationFrame libraryRow;
    private final Equipment equipment;
    private final ImagingFrameList imagingFrameList;
    private final LibraryTableModel tableModel;
    private final AppConfig appConfig;
    private final CalibrationType calType;
    private String prevPath = "";
    private String prevCamera = "";
    private CalibrationType prevCalibrationType;
    private int prevGain = 0;
    private double prevSubLength = 0;
    private double prevCameraTemp = 0;
    private int prevTotalSubs = 0;
    private JPanel mainPanel;
    private JComboBox<String> calibrationType;
    private CustomComboBox camera;
    private JButton saveButton;
    private JButton cancelButton;
    private JSpinner gain;
    private JSpinner subLength;
    private JSpinner totalSubs;
    private JLabel subLengthLabel;
    private JPanel fileChooser;
    private JSpinner cameraTemp;
    private JLabel cameraTempLabel;

    public LibraryRowEditor(CalibrationFrame libraryRow, Equipment equipment, ImagingFrameList imagingFrameList,
                            LibraryTableModel tableModel, AppConfig appConfig, CalibrationType calibrationType) {
        this.libraryRow = libraryRow;
        this.equipment = equipment;
        this.imagingFrameList = imagingFrameList;
        this.tableModel = tableModel;
        this.appConfig = appConfig;
        this.calType = calibrationType;

        edit = libraryRow != null;

        if (calType != null) { // TODO: clean up
            this.calibrationType.setEnabled(false);

            this.calibrationType.setSelectedItem(calibrationType.getName());
            boolean b = !Objects.equals(this.calibrationType.getSelectedItem(), CalibrationType.BIAS.getName());
            subLength.setEnabled(b);
            subLengthLabel.setEnabled(b);

            cameraTemp.setEnabled(b);
            cameraTempLabel.setEnabled(b);
        }

        setSpinnerModels();
        fillUpUI();
        handleActions();

        saveButton.setEnabled(false);
        setModal(true);
        add(mainPanel);
        if (edit) {
            setTitle("Edit Library Entry");
        } else {
            setTitle("Add New Library Entry");
        }
        setSize(500, 375);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateButtonState() {
        boolean notNull = this.camera.getSelectedItem() != null;
        boolean path = !((CustomFileChooser) fileChooser).getPath().equals(prevPath);
        boolean camera = !Objects.equals(this.camera.getSelectedItem(), prevCamera);
        boolean calibrationType = !Objects.equals(CalibrationType.getEnum(Objects.requireNonNull(this.calibrationType.getSelectedItem()).toString()), prevCalibrationType);
        boolean gain = !this.gain.getValue().equals(prevGain);
        boolean subLength = !this.subLength.getValue().equals(prevSubLength);
        boolean cameraTemp = !this.cameraTemp.getValue().equals(prevCameraTemp);
        boolean totalSubs = !this.totalSubs.getValue().equals(prevTotalSubs);

        if (edit) {
            saveButton.setEnabled(notNull && (path || camera || calibrationType || gain || subLength || cameraTemp || totalSubs));
        } else {
            saveButton.setEnabled(notNull && path && camera);
        }
    }

    private void fillUpUI() {
        if (libraryRow != null) {
            ((CustomFileChooser) fileChooser).setPath(libraryRow.getPath());
            prevPath = libraryRow.getPath();

            camera.setSelectedItem(libraryRow.getCamera(equipment).getViewName());
            prevCamera = libraryRow.getCamera(equipment).getViewName();

            calibrationType.setSelectedItem(libraryRow.getCalibrationType().getName());
            prevCalibrationType = libraryRow.getCalibrationType();

            gain.setValue(libraryRow.getGain());
            prevGain = libraryRow.getGain();

            if (prevCalibrationType == CalibrationType.DARK) {
                subLength.setValue(((DarkFrame) libraryRow).getSubLength());
                prevSubLength = ((DarkFrame) libraryRow).getSubLength();

                cameraTemp.setValue(((DarkFrame) libraryRow).getCameraTemp());
                prevCameraTemp = ((DarkFrame) libraryRow).getCameraTemp();
            }


            totalSubs.setValue(libraryRow.getTotalSubs());
            prevTotalSubs = libraryRow.getTotalSubs();
        }
    }

    private void setSpinnerModels() {
        gain.setModel(new SpinnerNumberModel(0,0,10000,1));
        subLength.setModel(new SpinnerNumberModel(0,0,10000,0.1));
        totalSubs.setModel(new SpinnerNumberModel(0,0,10000,1));
        cameraTemp.setModel(new SpinnerNumberModel(0,0,10000,0.1));
    }

    private void handleActions() {
        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {
            CalibrationType ct = CalibrationType.getEnum(Objects.requireNonNull(calibrationType.getSelectedItem()).toString());
            CalibrationFrame calibrationFrame = new CalibrationFrame();

            if (ct != null) {
                switch (ct) {
                    case DARK -> calibrationFrame = new DarkFrame();
                    case BIAS -> calibrationFrame = new BiasFrame();
                }
            }

            calibrationFrame.setPath(((CustomFileChooser) fileChooser).getPath());
            calibrationFrame.setCameraId(camera.getSelectedEquipmentItem().getId());
            calibrationFrame.setCalibrationType(CalibrationType.getEnum(Objects.requireNonNull(calibrationType.getSelectedItem()).toString()));
            calibrationFrame.setGain((Integer) gain.getValue());
            calibrationFrame.setTotalSubs((Integer) totalSubs.getValue());
            if (ct == CalibrationType.DARK) {
                ((DarkFrame) calibrationFrame).setSubLength((Double) subLength.getValue());
                if (appConfig.getTempType() == TempType.CELSIUS) {
                    ((DarkFrame) calibrationFrame).setCameraTemp((Double) cameraTemp.getValue());
                } else {
                    ((DarkFrame) calibrationFrame).setCameraTemp(Temp.toCelsius((Double) cameraTemp.getValue()));
                }
            }

            if (!checkForDuplicates(calibrationFrame)) {
                if (edit) {
                    imagingFrameList.removeCalibrationFrame(libraryRow, prevCalibrationType);
                }

                imagingFrameList.addCalibrationFrame(calibrationFrame);

                ImagingFrameStore.save(imagingFrameList, null);

                // if tableModel is null, the editor isn't in relation with any table
                if (tableModel != null) {
                    tableModel.fireTableDataChanged();
                }
                dispose();
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Such an entry already exists.",
                        "Invalid Entry",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        camera.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateButtonState();
            }
        });
        calibrationType.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateButtonState();
                boolean b = !Objects.equals(calibrationType.getSelectedItem(), CalibrationType.BIAS.getName());
                subLength.setEnabled(b);
                subLengthLabel.setEnabled(b);
                cameraTemp.setEnabled(b);
                cameraTempLabel.setEnabled(b);
                if (!b) {
                    subLength.setValue(0);
                    cameraTemp.setValue(0);
                }
            }
        });
        gain.addChangeListener(e -> updateButtonState());
        subLength.addChangeListener(e -> updateButtonState());
        cameraTemp.addChangeListener(e -> updateButtonState());
        totalSubs.addChangeListener(e -> updateButtonState());
    }

    private boolean checkForDuplicates(CalibrationFrame lib) {
        boolean camera;
        boolean calibrationType;
        boolean gain;
        boolean subLength;
        boolean cameraTemp;

        for (CalibrationFrame f : imagingFrameList.getCalibrationFrames()) {
            camera = false;
            calibrationType = false;
            gain = false;

            if (CalibrationType.getEnum(Objects.requireNonNull(this.calibrationType.getSelectedItem()).toString()) == CalibrationType.DARK) {
                subLength = false;
                cameraTemp = false;
            } else {
                subLength = true;
                cameraTemp = true;
            }

            if (f.getCamera(equipment).getViewName().equals(lib.getCamera(equipment).getViewName())) {
                camera = true;
            }
            if (f.getCalibrationType() == lib.getCalibrationType()) {
                calibrationType = true;
            }
            if (Objects.equals(f.getGain(), lib.getGain())) {
                gain = true;
            }

            if (CalibrationType.getEnum(Objects.requireNonNull(this.calibrationType.getSelectedItem()).toString()) == CalibrationType.DARK && calibrationType) {
                if (Objects.equals(((DarkFrame) f).getSubLength(), ((DarkFrame) lib).getSubLength())) {
                    subLength = true;
                }
                if (Objects.equals(((DarkFrame) f).getCameraTemp(), ((DarkFrame) lib).getCameraTemp())) {
                    cameraTemp = true;
                }
            }

            if (camera && calibrationType && gain && subLength && cameraTemp) {
                return true;
            }
        }

        return false;
    }

    private void createUIComponents() {
        camera = new CustomComboBox(EquipmentType.CAMERA, equipment);
        fileChooser = new CustomFileChooser(appConfig, "Choose Folder:") {
            @Override
            public void fileChanged() {
                updateButtonState();
            }
        };
    }
}
