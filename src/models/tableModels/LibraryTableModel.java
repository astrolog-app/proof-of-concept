package models.tableModels;

import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LibraryTableModel extends AbstractTableModel {
    // TODO: add sorting
    private final Equipment equipment;
    private final List<CalibrationFrame> data;

    public LibraryTableModel(Equipment equipment, List<CalibrationFrame> data) {
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
        CalibrationFrame lib = data.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> lib.getCamera(equipment).getViewName();
            case 1 -> lib.getCalibrationType().getName();
            case 2 -> lib.getGain();
            case 3 -> lib.getSubLength();
            case 4 -> lib.getTotalSubs();
            default -> null;
        };
    }

    public CalibrationFrame getLibraryRow(int rowIndex) {
        if (rowIndex == -1)
            return null;

        return data.get(rowIndex);
    }
}
