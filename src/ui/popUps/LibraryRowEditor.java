package ui.popUps;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;
import models.equipment.EquipmentType;
import ui.customComponents.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryRowEditor extends JDialog {
    private final CalibrationLibrary library;
    private final Equipment equipment;
    private JPanel mainPanel;
    private JComboBox<String> comboBox1;
    private JComboBox<String> camera;
    private JButton saveButton;
    private JButton cancelButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;

    public LibraryRowEditor(CalibrationLibrary library, Equipment equipment) {
        this.library = library;
        this.equipment = equipment;

        fillUpUI();
        handleActions();

        setModal(true);
        add(mainPanel);
        if (library != null) {
            setTitle("Edit Library Entry");
        } else {
            setTitle("Add New Library Entry");
        }
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fillUpUI() {
        if (library != null) {
            camera.setSelectedItem(library.getCamera(equipment));
        }
    }

    private void handleActions() {
        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {

        });
    }

    private void createUIComponents() {
        camera = new CustomComboBox(EquipmentType.CAMERA, equipment);
    }
}
