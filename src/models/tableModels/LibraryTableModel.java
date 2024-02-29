package models.tableModels;

import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;
import models.imagingFrames.DarkFrame;
import models.imagingFrames.ImagingFrameList;

import javax.swing.table.AbstractTableModel;

public class LibraryTableModel extends AbstractTableModel {
    // TODO: add sorting
    private final Equipment equipment;
    private final ImagingFrameList data;

    public LibraryTableModel(Equipment equipment, ImagingFrameList imagingFrameList) {
        this.equipment = equipment;
        this.data = imagingFrameList;
    }

    @Override
    public int getRowCount() {
        return data.getCalibrationFrames().size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Camera";
            case 1 -> "Calibration Type";
            case 2 -> "Gain";
            case 3 -> "Sub Length";
            case 4 -> "Camera Temp";
            case 5 -> "Total Subs";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CalibrationFrame lib = data.getCalibrationFrames().get(rowIndex);

        return switch (columnIndex) {
            case 0 -> lib.getCamera(equipment).getViewName();
            case 1 -> lib.getCalibrationType().getName();
            case 2 -> lib.getGain();
            case 3 -> {
                if (lib instanceof DarkFrame)
                    yield ((DarkFrame) lib).getSubLength();
                yield "-";
            }
            case 4 -> {
                if (lib instanceof DarkFrame)
                    yield ((DarkFrame) lib).getCameraTemp();
                yield "-";
            }
            case 5 -> lib.getTotalSubs();
            default -> null;
        };
    }

    public CalibrationFrame getLibraryRow(int rowIndex) {
        if (rowIndex == -1)
            return null;

        return data.getCalibrationFrames().get(rowIndex);
    }
}
