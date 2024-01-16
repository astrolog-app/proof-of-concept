package ui.popUps;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.equipment.EquipmentType;
import models.equipment.Telescope;
import services.fileHandler.EquipmentStore;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class EquipmentItemPanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JComboBox<String> brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public EquipmentItemPanel(EquipmentType equipmentType, Equipment equipment, EquipmentItem equipmentItem) {
        List<String> brandList = new ArrayList<>(equipment.getAllBrands());
        Collections.sort(brandList);

        for (String brand : brandList) {
            brandField.addItem(brand);
        }

        fillUp(equipmentItem, equipmentType);

        saveButton.setEnabled(true); // TODO: handle save button

        saveButton.addActionListener(e -> {
            int focalLength = Integer.parseInt(focalLengthField.getText());
            int aperture = Integer.parseInt(apertureField.getText());
            Telescope telescope = new Telescope(UUID.randomUUID(), true, nameField.getText(), "test", focalLength, aperture);
            equipment.addTelescope(telescope);
            EquipmentStore.save(equipment, null);
            dispose();
        });

        brandField.addActionListener(e -> brandField.removeItem(null));

        cancelButton.addActionListener(e -> this.dispose());

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add New " + equipmentType.getName());
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fillUp(EquipmentItem equipmentItem, EquipmentType equipmentType) {
        if (equipmentItem != null) {
            nameField.setText(equipmentItem.getName());
            brandField.setSelectedItem(equipmentItem.getBrand());

            switch (equipmentType) {
                // TODO: add switch cases
            }
        }
    }
}
