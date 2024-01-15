package ui.customComponents.equipmentTable;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.tableModels.EquipmentTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

public class EquipmentTable extends JTable {
    private final TableModel tableModel;
    public EquipmentTable(Equipment equipment, List<EquipmentItem> data) {
        tableModel = new EquipmentTableModel(data, equipment);

        createTable();
    }

    private void createTable() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(tableModel);

        setRowHeight(30);
        showHorizontalLines = true;
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
