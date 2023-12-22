package models.imagingSessionTable;

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

    public ImagingSessionTableModel() {
        data = ImagingSessionStore.loadImagingSessions();
        selectedColumns = ConfigurationStore.loadImagingSessionConfig().getSelectedColumns();
    }

    public void addSession(ImagingSession session) {
        data.add(session);
        //fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeSession(ImagingSession session) {
        int sessionIndex = -1;
        for (int i = 0; i < data.size(); i++) {
            System.out.println("test");
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
                case DATE -> is.getLightFrame().getDate();
                case TARGET -> is.getLightFrame().getTarget();
                case SUB_LENGTH -> formatDouble(is.getLightFrame().getSubLength());
                case TOTAL_SUBS -> is.getLightFrame().getTotalSubs();
                case TOTAL_EXPOSURE -> formatDouble(is.getLightFrame().getTotalSubs() * is.getLightFrame().getSubLength());
                case INTEGRATED_SUBS -> formatDouble(is.getLightFrame().getIntegratedSubs());
                case INTEGRATED_EXPOSURE -> formatDouble(is.getLightFrame().getIntegratedSubs() * is.getLightFrame().getSubLength());
                case FILTER -> is.getLightFrame().getFilter();
                case GAIN -> formatDouble(is.getLightFrame().getGain());
                case OFFSET -> formatDouble(is.getLightFrame().getOffset());
                case CAMERA_TEMP -> formatDouble(is.getLightFrame().getCameraTemp());
                case OUTSIDE_TEMP -> formatDouble(is.getLightFrame().getOutsideTemp());
                case AVERAGE_SEEING -> formatDouble(is.getLightFrame().getAverageSeeing());
                case AVERAGE_CLOUD_COVER -> formatDouble(is.getLightFrame().getAverageCloudCover());
                case AVERAGE_MOON -> formatDouble(is.getLightFrame().getAverageMoon());
                case TELESCOPE -> is.getLightFrame().getTelescope();
                case FLATTENER -> is.getLightFrame().getFlattener();
                case CAMERA -> is.getLightFrame().getCamera();
                case NOTES -> is.getLightFrame().getNotes();
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
