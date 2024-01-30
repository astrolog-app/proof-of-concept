package ui.customComponents.equipmentTable;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.equipment.EquipmentType;
import ui.popUps.EquipmentItemPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentTableWrapper {
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
        List<EquipmentItem> data = new ArrayList<>();

        switch (equipmentType) {
            case TELESCOPE -> data = new ArrayList<>(equipment.getTelescopes().values());
            case CAMERA -> data = new ArrayList<>(equipment.getCameras());
            case FLATTENER -> data = new ArrayList<>(equipment.getFlatteners());
            case MOUNT -> data = new ArrayList<>(equipment.getMounts());
            case FILTER -> data = new ArrayList<>(equipment.getFilters());
            case ACCESSOIRE -> data = new ArrayList<>(equipment.getAccessoires());
        }

        equipmentTable1 = new EquipmentTable(equipment, data, equipmentType);
    }
}
