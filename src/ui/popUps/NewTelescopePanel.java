package ui.popUps;

import models.equipment.Equipment;
import models.equipment.Telescope;
import services.fileHandler.EquipmentStore;

import javax.swing.*;

public class NewTelescopePanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JComboBox brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public NewTelescopePanel(Equipment equipment, EquipmentStore equipmentStore) {
        saveButton.setEnabled(false);

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add new Telescope");
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        if (nameField.getText() != null) {
            setEnabled(true);
        }

        saveButton.addActionListener(e -> {
            int focalLength = Integer.parseInt(focalLengthField.getText());
            int aperture = Integer.parseInt(apertureField.getText());
            Telescope telescope = new Telescope(nameField.getText(), "test", focalLength, aperture);
            equipment.addTelescope(telescope);
            equipmentStore.save();
            dispose();
        });
        cancelButton.addActionListener(e -> this.dispose());
    }
}
