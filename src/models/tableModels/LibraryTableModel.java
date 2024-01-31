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
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Camera";
            case 1 -> "Calibration Type";
            case 2 -> "Gain";
            case 3 -> "Sub Length";
            case 4 -> "Total Subs";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CalibrationLibrary lib = data.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> lib.getCamera(equipment);
            case 1 -> lib.getCalibrationType().getName();
            case 2 -> lib.getGain();
            case 3 -> lib.getSubLength();
            case 4 -> lib.getTotalSubs();
            default -> null;
        };
    }
}
