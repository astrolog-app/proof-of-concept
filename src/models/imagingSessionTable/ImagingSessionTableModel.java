package models.imagingSessionTable;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.imagingSessions.ImagingSession;
import models.settings.LoggerColumns;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.ImagingSessionStore;
import utils.Enums;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.List;

public class ImagingSessionTableModel extends AbstractTableModel {
    private final List<ImagingSession> data;
    private final List<LoggerColumns> selectedColumns;
    private final Equipment equipment;

    public ImagingSessionTableModel(List<ImagingSession> imagingSessions, Equipment equipment) {
        data = imagingSessions;
        selectedColumns = ConfigurationStore.loadImagingSessionConfig().getSelectedColumns();
        this.equipment = equipment;
    }

    public void addSession(ImagingSession session) {
        data.add(session);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeSession(ImagingSession session) {
        int sessionIndex = -1;
        for (int i = 0; i < data.size(); i++) {
            if (session == data.get(i)) {
                sessionIndex = i;
                break;
            }
        }

        data.remove(session);

        if (sessionIndex != -1) {
            fireTableRowsDeleted(sessionIndex, sessionIndex);
        }
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
        return Enums.enumToString(selectedColumns.get(column));
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data != null) {
            ImagingSession is = data.get(rowIndex);
            LoggerColumns lc = selectedColumns.get(columnIndex);

            return switch (lc) {
                case DATE -> formatString(is.getLightFrame().getDate());
                case TARGET -> formatString(is.getLightFrame().getTarget());
                case SUB_LENGTH -> formatDouble(is.getLightFrame().getSubLength());
                case TOTAL_SUBS -> formatDouble(is.getLightFrame().getTotalSubs());
                case TOTAL_EXPOSURE -> formatDouble(calculateTotalExposure(is.getLightFrame().getTotalSubs(), is.getLightFrame().getSubLength()));
                case INTEGRATED_SUBS -> formatDouble(is.getLightFrame().getIntegratedSubs());
                case INTEGRATED_EXPOSURE -> formatDouble(calculateTotalExposure(is.getLightFrame().getIntegratedSubs(), is.getLightFrame().getSubLength()));
                case FILTER -> formatString(buildString(is.getLightFrame().getFilter(equipment)));
                case GAIN -> formatDouble(is.getLightFrame().getGain());
                case OFFSET -> formatDouble(is.getLightFrame().getOffset());
                case CAMERA_TEMP -> formatDouble(is.getLightFrame().getCameraTemp());
                case OUTSIDE_TEMP -> formatDouble(is.getLightFrame().getOutsideTemp());
                case AVERAGE_SEEING -> formatDouble(is.getLightFrame().getAverageSeeing());
                case AVERAGE_CLOUD_COVER -> formatDouble(is.getLightFrame().getAverageCloudCover());
                case AVERAGE_MOON -> formatDouble(is.getLightFrame().getAverageMoon());
                case TELESCOPE -> formatString(buildString(is.getLightFrame().getTelescope(equipment)));
                case FLATTENER -> formatString(buildString(is.getLightFrame().getFlattener(equipment)));
                case CAMERA -> formatString(buildString(is.getLightFrame().getCamera(equipment)));
                case NOTES -> formatString(is.getLightFrame().getNotes());
            };
        }

        return null;
    }

    private String buildString(Object o) {
        if (o != null) {
            return ((EquipmentItem) o).getBrand() + " " + ((EquipmentItem) o).getName();
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

    private String formatString(String s) {
        if (s == null) {
            return "N/A";
        }

        return s;
    }

    private Double calculateTotalExposure(Double totalSubs, Double subLength) {
        if (totalSubs == null || subLength == null) {
            return null;
        }

        return totalSubs * subLength;
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
