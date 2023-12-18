package ui.customComponents;

import models.imagingSessions.ImagingSession;
import models.settings.LoggerColumns;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.ImagingSessionStore;
import utils.Enums;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ImagingSessionTableModel extends AbstractTableModel {
    private List<ImagingSession> sessions;
    private String[] columnNames;
    private final List<LoggerColumns> selectedColumns;
    private Object[][] tableData;

    public ImagingSessionTableModel() {
        sessions = new ArrayList<>();
        selectedColumns = ConfigurationStore.loadImagingSessionConfig().getSelectedColumns();

        setColumnNames();
        setTableContent();
    }

    private void setColumnNames() {
        columnNames = new String[selectedColumns.size()];
        for (int i = 0; i < selectedColumns.size(); i++) {
            columnNames[i] = Enums.enumToString(selectedColumns.get(i));
        }
    }

    private void setTableContent() {
        sessions = ImagingSessionStore.loadImagingSessions();

        if (sessions != null) {
            tableData = new Object[sessions.size()][selectedColumns.size()];

            int isProgress = 0;
            int lcProgress = 0;

            for (ImagingSession is : sessions) {
                for (LoggerColumns lc : selectedColumns) {
                    switch (lc) {
                        case DATE -> tableData[isProgress][lcProgress] = is.getLightFrame().getDate();
                        case TARGET -> tableData[isProgress][lcProgress] = is.getLightFrame().getTarget();
                        case SUB_LENGTH -> tableData[isProgress][lcProgress] = is.getLightFrame().getSubLength();
                        case TOTAL_SUBS -> tableData[isProgress][lcProgress] = is.getLightFrame().getTotalSubs();
                        case TOTAL_EXPOSURE -> tableData[isProgress][lcProgress] = is.getLightFrame().getTotalSubs() * is.getLightFrame().getSubLength();
                        case INTEGRATED_SUBS -> tableData[isProgress][lcProgress] = is.getLightFrame().getIntegratedSubs();
                        case INTEGRATED_EXPOSURE -> tableData[isProgress][lcProgress] = is.getLightFrame().getIntegratedSubs() * is.getLightFrame().getSubLength();
                        case FILTER -> tableData[isProgress][lcProgress] = is.getLightFrame().getFilter();
                        case GAIN -> tableData[isProgress][lcProgress] = is.getLightFrame().getGain();
                        case OFFSET -> tableData[isProgress][lcProgress] = is.getLightFrame().getOffset();
                        case CAMERA_TEMP -> tableData[isProgress][lcProgress] = is.getLightFrame().getCameraTemp();
                        case OUTSIDE_TEMP -> tableData[isProgress][lcProgress] = is.getLightFrame().getOutsideTemp();
                        case AVERAGE_SEEING -> tableData[isProgress][lcProgress] = is.getLightFrame().getAverageSeeing();
                        case AVERAGE_CLOUD_COVER -> tableData[isProgress][lcProgress] = is.getLightFrame().getAverageCloudCover();
                        case AVERAGE_MOON -> tableData[isProgress][lcProgress] = is.getLightFrame().getAverageMoon();
                        case TELESCOPE -> tableData[isProgress][lcProgress] = is.getLightFrame().getTelescope();
                        case FLATTENER -> tableData[isProgress][lcProgress] = is.getLightFrame().getFlattener();
                        case CAMERA -> tableData[isProgress][lcProgress] = is.getLightFrame().getCamera();
                        case NOTES -> tableData[isProgress][lcProgress] = is.getLightFrame().getNotes();
                    }
                    lcProgress++;
                }
                lcProgress = 0;
                isProgress++;
            }
        } else {
            // define content if imaging session are null
        }
    }

    public void addSession(ImagingSession session) {
        sessions.add(session);
        fireTableRowsInserted(sessions.size() - 1, sessions.size() - 1);
    }

    public void removeSession(ImagingSession session) {
        sessions.remove(session);
        //fireTableRowsInserted(sessions.size() - 1, sessions.size() - 1);
    }

    @Override
    public int getRowCount() {
        return sessions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData[rowIndex][columnIndex];
    }

    public ImagingSession getSession(int rowIndex) {
        return sessions.get(rowIndex);
    }

    public List<LoggerColumns> getSelectedColumns() {
        return selectedColumns;
    }

    public Object[][] getTableData() {
        return tableData;
    }
}
