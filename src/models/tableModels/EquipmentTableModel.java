package models.tableModels;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import utils.EquipmentItems;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EquipmentTableModel extends AbstractTableModel {
    private final List<EquipmentItem> data;
    private final Equipment equipment;

    public EquipmentTableModel(List<EquipmentItem> data, Equipment equipment) {
        this.data = data;
        this.equipment = equipment;
    }

    public void addItem(EquipmentItem equipmentItem) {
        data.add(equipmentItem);
        // TODO: add it in the json file
    }

    public void editItem() {}

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        if (!data.isEmpty()) {
            return data.get(0).getProperties().length + 1;
        }

        return 0;
    }

    @Override
    public String getColumnName(int column) {
        if (!data.isEmpty()) {
            if (column == 0)
                return "Name";
            return data.get(0).getPropertyNames()[column - 1];
        }

        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return EquipmentItems.constructName(data.get(rowIndex));
        }

        return data.get(rowIndex).getProperties()[columnIndex - 1];
    }
}
