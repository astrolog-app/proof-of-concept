package ui.customComponents.equipmentTable;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.equipment.EquipmentType;
import models.tableModels.EquipmentTableModel;

import javax.swing.*;
import java.util.List;

public class EquipmentTable extends JTable {
    private final EquipmentTableModel tableModel;
    private final EquipmentType equipmentType;

    public EquipmentTable(Equipment equipment, List<EquipmentItem> data, EquipmentType equipmentType) {
        tableModel = new EquipmentTableModel(data, equipment, equipmentType);
        this.equipmentType = equipmentType;

        createTable();
    }

    private void createTable() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(tableModel);

        setRowHeight(30);
        showHorizontalLines = true;
    }

    public EquipmentTableModel getTableModel() {
        return tableModel;
    }
}
