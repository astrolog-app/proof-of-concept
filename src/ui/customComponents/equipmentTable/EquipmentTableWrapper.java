package ui.customComponents.equipmentTable;

import models.equipment.Equipment;
import models.equipment.EquipmentType;
import ui.popUps.EquipmentItemPanel;

import javax.swing.*;
import java.util.ArrayList;

public class EquipmentTableWrapper extends JScrollPane {
    private final Equipment equipment;
    private final EquipmentType equipmentType;

    private JButton addButton;
    private JPanel mainPanel;
    private JButton editButton;
    private EquipmentTable equipmentTable1;

    public EquipmentTableWrapper(Equipment equipment, EquipmentType equipmentType) {
        this.equipment = equipment;
        this.equipmentType = equipmentType;

        updateEditButtonState();

        handleActions();

        add(mainPanel);
    }

    private void updateEditButtonState() {
        boolean b = equipmentTable1.getTableModel().getEquipmentItem(equipmentTable1.getSelectedRow()) != null;
        editButton.setEnabled(b);
    }

    private void handleActions() {
        addButton.addActionListener(e -> new EquipmentItemPanel(equipmentType, equipment, null));

        editButton.addActionListener(e -> new EquipmentItemPanel(
                equipmentType,
                equipment,
                equipmentTable1.getTableModel().getEquipmentItem(equipmentTable1.getSelectedRow())
        ));

        equipmentTable1.getSelectionModel().addListSelectionListener(e -> updateEditButtonState());
    }

    private void createUIComponents() {
        equipmentTable1 = new EquipmentTable(equipment, new ArrayList<>(equipment.getTelescopes()), equipmentType);
    }
}
