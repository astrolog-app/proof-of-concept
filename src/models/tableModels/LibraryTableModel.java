package models.tableModels;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LibraryTableModel extends AbstractTableModel {
    private final Equipment equipment;
    private final List<CalibrationLibrary> data;

    public LibraryTableModel(Equipment equipment, List<CalibrationLibrary> data) {
        this.equipment = equipment;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CalibrationLibrary lib = data.get(rowIndex);

        switch (columnIndex) {
            // TODO: to implement
        }

        return null;
    }
}
