package ui.popUps;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.equipment.EquipmentType;
import models.equipment.Telescope;
import services.fileHandler.EquipmentStore;
import utils.Enums;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class NewEquipmentItemPanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JComboBox<String> brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public NewEquipmentItemPanel(EquipmentType equipmentType, Equipment equipment) {
        List<String> brandList = new ArrayList<>(equipment.getAllBrands());
        Collections.sort(brandList);

        for (String brand : brandList) {
            brandField.addItem(brand);
        }

        saveButton.setEnabled(true);

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
        setTitle("Add New " + Enums.enumToString(equipmentType));
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
