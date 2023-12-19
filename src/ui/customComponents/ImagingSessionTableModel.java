package ui.customComponents;

import models.imagingSessions.ImagingSession;
import models.settings.ImagingSessionConfig;
import models.settings.LoggerColumns;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.ImagingSessionStore;
import utils.Enums;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagingSessionTableModel extends AbstractTableModel {
    private final List<ImagingSession> data;
    private final Map<Integer, ImagingSession> rowToInstanceMap = new HashMap<>();
    private final List<LoggerColumns> selectedColumns;
    private final Map<Integer, LoggerColumns> columnToEnumMap = new HashMap<>();

    public ImagingSessionTableModel() {
        data = ImagingSessionStore.loadImagingSessions();
        selectedColumns = ConfigurationStore.loadImagingSessionConfig().getSelectedColumns();

        updateRowMapping();
        updateColumnMapping();
        setColumnsWidth();
    }

    public void updateRowMapping() {
        rowToInstanceMap.clear();
        for (int i = 0; i < data.size(); i++) {
            rowToInstanceMap.put(i, data.get(i));
        }
    }

    public void updateColumnMapping() {
        columnToEnumMap.clear();
        for (int i = 0; i < selectedColumns.size(); i++) {
            columnToEnumMap.put(i, selectedColumns.get(i));
        }
    }

    private void setColumnsWidth() {
        ImagingSessionConfig isConfig = ConfigurationStore.loadImagingSessionConfig();
        LoggerColumns defaultSortedColumns;
        SortOrder columnSortingType;
        if (isConfig != null) {
            defaultSortedColumns = isConfig.getDefaultSortedColumn();
            columnSortingType = isConfig.getColumnSortingType();
        } else  {
            defaultSortedColumns = LoggerColumns.DATE;
            columnSortingType = SortOrder.DESCENDING;
        }
    }

    public void addSession(ImagingSession session) {
        data.add(session);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        updateRowMapping();
    }

    public void removeSession(ImagingSession session) {
        data.remove(session);
        //fireTableRowsInserted(sessions.size() - 1, sessions.size() - 1);
        updateRowMapping();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnToEnumMap.size();
    }

    @Override
    public String getColumnName(int column) {
        return Enums.enumToString(columnToEnumMap.get(column));
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data != null) {
            ImagingSession is = rowToInstanceMap.get(rowIndex);
            LoggerColumns lc = columnToEnumMap.get(columnIndex);

            return switch (lc) {
                case DATE -> is.getLightFrame().getDate();
                case TARGET -> is.getLightFrame().getTarget();
                case SUB_LENGTH -> is.getLightFrame().getSubLength();
                case TOTAL_SUBS -> is.getLightFrame().getTotalSubs();
                case TOTAL_EXPOSURE -> is.getLightFrame().getTotalSubs() * is.getLightFrame().getSubLength();
                case INTEGRATED_SUBS -> is.getLightFrame().getIntegratedSubs();
                case INTEGRATED_EXPOSURE -> is.getLightFrame().getIntegratedSubs() * is.getLightFrame().getSubLength();
                case FILTER -> is.getLightFrame().getFilter();
                case GAIN -> is.getLightFrame().getGain();
                case OFFSET -> is.getLightFrame().getOffset();
                case CAMERA_TEMP -> is.getLightFrame().getCameraTemp();
                case OUTSIDE_TEMP -> is.getLightFrame().getOutsideTemp();
                case AVERAGE_SEEING -> is.getLightFrame().getAverageSeeing();
                case AVERAGE_CLOUD_COVER -> is.getLightFrame().getAverageCloudCover();
                case AVERAGE_MOON -> is.getLightFrame().getAverageMoon();
                case TELESCOPE -> is.getLightFrame().getTelescope();
                case FLATTENER -> is.getLightFrame().getFlattener();
                case CAMERA -> is.getLightFrame().getCamera();
                case NOTES -> is.getLightFrame().getNotes();
            };
        } else {
            return null;
            // define content if imaging session are null
        }
    }

    public ImagingSession getSession(int rowIndex) {
        return data.get(rowIndex);
    }

    public List<LoggerColumns> getSelectedColumns() {
        return selectedColumns;
    }
}
