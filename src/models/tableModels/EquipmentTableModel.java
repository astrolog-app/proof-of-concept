package models.tableModels;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.equipment.EquipmentType;
import utils.EquipmentItems;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EquipmentTableModel extends AbstractTableModel {
    private final List<EquipmentItem> data;
    private final Equipment equipment;
    private final EquipmentType equipmentType;

    public EquipmentTableModel(List<EquipmentItem> data, Equipment equipment, EquipmentType equipmentType) {
        this.data = data;
        this.equipment = equipment;
        this.equipmentType = equipmentType;
    }

    public void addItem(EquipmentItem equipmentItem) {
        data.add(equipmentItem);
        fireTableDataChanged();

        // TODO: add it in the json file
    }

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

    public EquipmentItem getEquipmentItem(int rowIndex) {
        if (rowIndex == -1)
            return null;

        return data.get(rowIndex);
    }
}
