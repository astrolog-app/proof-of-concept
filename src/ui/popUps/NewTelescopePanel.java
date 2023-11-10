package ui.popUps;

import models.equipment.Equipment;
import models.equipment.Telescope;
import services.fileHandler.EquipmentStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class NewTelescopePanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JTextField brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public NewTelescopePanel(Equipment equipment, EquipmentStore equipmentStore) {
//        saveButton.setEnabled(false);

        setContentPane(mainPanel);
        setMinimumSize(new Dimension(200,125));
        setSize(500, 275);
        setLocationRelativeTo(null);
        setVisible(true);

        if (nameField.getText() != null) {
            setEnabled(true);
        }

        saveButton.addActionListener(e -> {
            int focalLength = Integer.parseInt(focalLengthField.getText());
            int aperture = Integer.parseInt(apertureField.getText());
            Telescope telescope = new Telescope(nameField.getText(), brandField.getText(), focalLength, aperture);
            equipment.addTelescope(telescope);
            equipmentStore.save();
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());
    }
}
