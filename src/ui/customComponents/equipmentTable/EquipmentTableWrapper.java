package ui.customComponents.equipmentTable;

import models.equipment.Equipment;

import javax.swing.*;
import java.util.ArrayList;

public class EquipmentTableWrapper extends JScrollPane {
    private final Equipment equipment;

    private JButton addButton;
    private JPanel mainPanel;
    private JButton editButton;
    private EquipmentTable equipmentTable1;

    public EquipmentTableWrapper(Equipment equipment) {
        this.equipment = equipment;

        add(mainPanel);
    }

    private void createUIComponents() {
        equipmentTable1 = new EquipmentTable(equipment, new ArrayList<>(equipment.getTelescopes()));
    }
}
