package models.tableModels;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.imagingSessions.ImagingSession;
import models.imagingSessions.LightFrame;
import models.settings.ImagingSessionConfig;
import models.settings.LoggerColumns;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

public class ImagingSessionTableModel extends AbstractTableModel {
    private final List<ImagingSession> data;
    private final List<LoggerColumns> selectedColumns;
    private final Equipment equipment;

    public ImagingSessionTableModel(List<ImagingSession> imagingSessions, Equipment equipment, ImagingSessionConfig isConfig) {
        data = imagingSessions;
        selectedColumns = isConfig.getSelectedColumns(); // TODO: change
        this.equipment = equipment;
    }

    public void removeSession(ImagingSession session) {
        data.remove(session);
        fireTableDataChanged();
    }

    public void updateDataSorting(LoggerColumns sortedColumn, SortOrder sortingDirection) {

    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return selectedColumns.size();
    }

    @Override
    public String getColumnName(int column) {
        return selectedColumns.get(column).getName();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data != null) {
            ImagingSession is = data.get(rowIndex);
            LoggerColumns lc = selectedColumns.get(columnIndex);
            LightFrame lf = is.getLightFrame();

            return switch (lc) {
                case DATE -> formatString(lf.getDate());
                case TARGET -> formatString(lf.getTarget());
                case SUB_LENGTH -> formatDouble(lf.getSubLength());
                case TOTAL_SUBS -> lf.getTotalSubs();
                case TOTAL_EXPOSURE -> formatDouble(calculateTotalExposure(lf.getTotalSubs(), lf.getSubLength()));
                case INTEGRATED_SUBS -> lf.getIntegratedSubs();
                case INTEGRATED_EXPOSURE -> formatDouble(calculateTotalExposure(lf.getIntegratedSubs(), lf.getSubLength()));
                case FILTER -> formatEquipmentName(lf.getFilter(equipment));
                case GAIN -> formatDouble(lf.getGain());
                case OFFSET -> formatDouble(lf.getOffset());
                case CAMERA_TEMP -> formatDouble(lf.getCameraTemp());
                case OUTSIDE_TEMP -> formatDouble(lf.getOutsideTemp());
                case AVERAGE_SEEING -> formatDouble(lf.getAverageSeeing());
                case AVERAGE_CLOUD_COVER -> formatDouble(lf.getAverageCloudCover());
                case AVERAGE_MOON -> formatDouble(lf.getAverageMoon());
                case TELESCOPE -> formatEquipmentName(lf.getTelescope(equipment));
                case FLATTENER -> formatEquipmentName(lf.getFlattener(equipment));
                case MOUNT -> formatEquipmentName(lf.getMount(equipment));
                case CAMERA -> formatEquipmentName(lf.getCamera(equipment));
                case NOTES -> formatString(lf.getNotes());
            };
        }

        return null;
    }

    private String formatDouble(Double d) {
        DecimalFormat format = new DecimalFormat("0.#");

        if (d != null) {
            String formattedValue = format.format(d);

            formattedValue = formattedValue.replaceAll("\\.0*$", "");

            return formattedValue;
        } else {
            return "N/A";
        }
    }

    private String formatEquipmentName(EquipmentItem item) {
        if (item == null)
            return "N/A";

        return item.getViewName();
    }

    private String formatString(String s) {
        if (s == null) {
            return "N/A";
        }

        return s;
    }

    private Double calculateTotalExposure(Integer totalSubs, Double subLength) {
        if (totalSubs == null || subLength == null) {
            return null;
        }

        return Double.valueOf(totalSubs) * subLength;
    }

    public ImagingSession getSession(int rowIndex) {
        if (rowIndex == -1)
            return null;

        return data.get(rowIndex);
    }

    public int getColumnAt(LoggerColumns lc) {
        for (int i = 0; i < selectedColumns.size(); i++) {
            if (selectedColumns.get(i) == lc) {
                return i;
            }
        }

        return -1;
    }

    public List<LoggerColumns> getSelectedColumns() {
        return selectedColumns;
    }
}
