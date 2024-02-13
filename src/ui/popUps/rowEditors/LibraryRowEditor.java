package ui.popUps.rowEditors;

import models.calibrationLibrary.CalibrationLibrary;
import models.calibrationLibrary.CalibrationType;
import models.equipment.Equipment;
import models.equipment.EquipmentType;
import models.settings.AppConfig;
import models.tableModels.LibraryTableModel;
import services.fileHandler.CalibrationLibraryStore;
import ui.customComponents.CustomComboBox;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibraryRowEditor extends JDialog {
    private final boolean edit;
    private final CalibrationLibrary libraryRow;
    private final Equipment equipment;
    private final List<CalibrationLibrary> library;
    private final LibraryTableModel tableModel;
    private final AppConfig appConfig;
    private final CalibrationType calType;
    private String prevPath = "";
    private String prevCamera = "";
    private String prevCalibrationType = "Flat";
    private int prevGain = 0;
    private int prevSubLength = 0;
    private int prevTotalSubs = 0;
    private JPanel mainPanel;
    private JComboBox<String> calibrationType;
    private CustomComboBox camera;
    private JButton saveButton;
    private JButton cancelButton;
    private JSpinner gain;
    private JSpinner subLength;
    private JSpinner totalSubs;
    private JTextField pathField;
    private JButton changeButton;
    private JLabel subLengthLabel;

    public LibraryRowEditor(CalibrationLibrary libraryRow, Equipment equipment, List<CalibrationLibrary> library,
                            LibraryTableModel tableModel, AppConfig appConfig, CalibrationType calibrationType) {
        this.libraryRow = libraryRow;
        this.equipment = equipment;
        this.library = library;
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
        setSize(500, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateButtonState() {
        boolean notNull = this.camera.getSelectedItem() != null;
        boolean path = !pathField.getText().equals(prevPath);
        boolean camera = !Objects.equals(this.camera.getSelectedItem(), prevCamera);
        boolean calibrationType = !Objects.equals(this.calibrationType.getSelectedItem(), prevCalibrationType);
        boolean gain = !this.gain.getValue().equals(prevGain);
        boolean subLength = !this.subLength.getValue().equals(prevSubLength);
        boolean totalSubs = !this.totalSubs.getValue().equals(prevTotalSubs);

        if (edit) {
            saveButton.setEnabled(notNull && (path || camera || calibrationType || gain || subLength || totalSubs));
        } else {
            saveButton.setEnabled(notNull && path && camera);
        }
    }

    private void fillUpUI() {
        if (libraryRow != null) {
            pathField.setText(libraryRow.getPath());
            prevPath = libraryRow.getPath();

            camera.setSelectedItem(libraryRow.getCamera(equipment).getViewName());
            prevCamera = libraryRow.getCamera(equipment).getViewName();

            calibrationType.setSelectedItem(libraryRow.getCalibrationType().getName());
            prevCalibrationType = libraryRow.getCalibrationType().getName();

            gain.setValue(libraryRow.getGain());
            prevGain = libraryRow.getGain();

            subLength.setValue(libraryRow.getSubLength());
            prevSubLength = libraryRow.getSubLength();

            totalSubs.setValue(libraryRow.getTotalSubs());
            prevTotalSubs = libraryRow.getTotalSubs();
        }
    }

    private void setSpinnerModels() {
        gain.setModel(new SpinnerNumberModel(0,0,10000,1));
        subLength.setModel(new SpinnerNumberModel(0,0,10000,1));
        totalSubs.setModel(new SpinnerNumberModel(0,0,10000,1));
    }

    private void handleActions() {
        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {
            CalibrationLibrary calibrationLibrary = new CalibrationLibrary();

            calibrationLibrary.setPath(pathField.getText());
            calibrationLibrary.setCameraId(camera.getSelectedEquipmentItem().getId());
            calibrationLibrary.setCalibrationType(CalibrationType.getEnum(Objects.requireNonNull(calibrationType.getSelectedItem()).toString()));
            calibrationLibrary.setGain((Integer) gain.getValue());
            calibrationLibrary.setSubLength((Integer) subLength.getValue());
            calibrationLibrary.setTotalSubs((Integer) totalSubs.getValue());

            if (!checkForDuplicates(calibrationLibrary)) {
                if (edit) {
                    library.remove(libraryRow);
                }
                library.add(calibrationLibrary);

                CalibrationLibraryStore.save(library, null);

                // if tableModel is null, the editor isn't in relation with any table
                if (tableModel != null) {
                    tableModel.fireTableDataChanged();
                }
                dispose();
            } else {
                // TODO: gain and sub length get neglected
                JOptionPane.showConfirmDialog(null,
                        "An entry with this camera and calibration type already exists.",
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
                if (!b) {
                    subLength.setValue(0);
                }
            }
        });
        gain.addChangeListener(e -> updateButtonState());
        subLength.addChangeListener(e -> updateButtonState());
        totalSubs.addChangeListener(e -> updateButtonState());

        changeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Folder");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showDialog(null, "Select");
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                pathField.setText(selectedFolder.getAbsolutePath().replace(appConfig.getFolderPath(),""));
                updateButtonState();
            }
        });
    }

    private boolean checkForDuplicates(CalibrationLibrary lib) {
        for (CalibrationLibrary l : library) {
            if (l.getCalibrationType() == lib.getCalibrationType()) {
                if (l.getCamera(equipment).getViewName().equals(lib.getCamera(equipment).getViewName())) {
                    return true;
                }
            }
        }

        return false;
    }

    private void createUIComponents() {
        camera = new CustomComboBox(EquipmentType.CAMERA, equipment);
    }
}
