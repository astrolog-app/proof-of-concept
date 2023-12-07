package ui.popUps;

import models.equipment.Equipment;
import models.equipment.Telescope;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewTelescopePanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JComboBox brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public NewTelescopePanel(Equipment equipment) {
        List<String> brandList = new ArrayList<>(equipment.getAllBrands());
        Collections.sort(brandList);

        brandField.addItem("Select Brand");
        brandField.addItem("Add New");
        for (String brand : brandList) {
            brandField.addItem(brand);
        }

        saveButton.setEnabled(false);

        saveButton.addActionListener(e -> {
            int focalLength = Integer.parseInt(focalLengthField.getText());
            int aperture = Integer.parseInt(apertureField.getText());
            Telescope telescope = new Telescope(nameField.getText(), "test", focalLength, aperture);
            equipment.addTelescope(telescope);
            //EquipmentStore.save(equipment, null);
            dispose();
        });

        cancelButton.addActionListener(e -> this.dispose());

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add New Telescope");
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
