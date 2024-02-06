package ui.popUps;

import models.calibrationLibrary.CalibrationLibrary;
import models.calibrationLibrary.CalibrationType;
import models.equipment.Equipment;
import models.equipment.EquipmentType;
import services.fileHandler.CalibrationLibraryStore;
import ui.customComponents.CustomComboBox;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class LibraryRowEditor extends JDialog {
    private final CalibrationLibrary libraryRow;
    private final Equipment equipment;
    private final List<CalibrationLibrary> library;
    private String prevCamera = "";
    private String prevCalibrationType = "Flat";
    private int prevGain = 0;
    private int prevSubLength = 0;
    private int prevTotalSubs = 0;
    private JPanel mainPanel;
    private JComboBox<String> calibrationType;
    private JComboBox<String> camera;
    private JButton saveButton;
    private JButton cancelButton;
    private JSpinner gain;
    private JSpinner subLength;
    private JSpinner totalSubs;

    public LibraryRowEditor(CalibrationLibrary libraryRow, Equipment equipment, List<CalibrationLibrary> library) {
        this.libraryRow = libraryRow;
        this.equipment = equipment;
        this.library = library;

        fillUpUI();
        handleActions();

        saveButton.setEnabled(false);
        setModal(true);
        add(mainPanel);
        if (libraryRow != null) {
            setTitle("Edit Library Entry");
        } else {
            setTitle("Add New Library Entry");
        }
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateButtonState() {
        boolean camera = this.camera.getSelectedItem() != null && this.camera.getSelectedItem() != prevCamera;
        boolean calibrationType = this.calibrationType.getSelectedItem() != prevCalibrationType;

        if (camera && calibrationType) {
            saveButton.setEnabled(true);
        } else {
            saveButton.setEnabled(false);
        }
    }

    private void fillUpUI() {
        if (libraryRow != null) {
            camera.setSelectedItem(libraryRow.getCamera(equipment));
            prevCamera = libraryRow.getCamera(equipment).getName();

            calibrationType.setSelectedItem(libraryRow.getCalibrationType().getName());
            prevCalibrationType = libraryRow.getCalibrationType().getName();
        }
    }

    private void handleActions() {
        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {
            CalibrationLibrary calibrationLibrary = new CalibrationLibrary();

            calibrationLibrary.setPath("");
            calibrationLibrary.setCameraId(UUID.randomUUID());
            calibrationLibrary.setCalibrationType(CalibrationType.getEnum(Objects.requireNonNull(calibrationType.getSelectedItem()).toString()));
            calibrationLibrary.setGain((Integer) gain.getValue());
            calibrationLibrary.setSubLength((Integer) subLength.getValue());
            calibrationLibrary.setTotalSubs((Integer) totalSubs.getValue());

            library.add(calibrationLibrary);
            CalibrationLibraryStore.save(library, null);
        });

        camera.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateButtonState();
            }
        });
        calibrationType.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateButtonState();
            }
        });
        gain.addChangeListener(e -> updateButtonState());
        subLength.addChangeListener(e -> updateButtonState());
        totalSubs.addChangeListener(e -> updateButtonState());
    }

    private void createUIComponents() {
        camera = new CustomComboBox(EquipmentType.CAMERA, equipment);
    }
}
