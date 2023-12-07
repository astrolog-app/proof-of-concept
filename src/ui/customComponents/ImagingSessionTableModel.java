package ui.customComponents;

import models.imagingSessions.ImagingSession;
import models.settings.LoggerColumns;
import services.fileHandler.ConfigurationStore;
import services.imagingSessions.TableData;
import utils.Enums;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ImagingSessionTableModel extends AbstractTableModel {
    private final List<ImagingSession> sessions;
    private final String[] columnNames;
    private final List<LoggerColumns> selectedColumns;
    private final Object[][] tableData;

    public ImagingSessionTableModel() {
        sessions = new ArrayList<>();
        selectedColumns = ConfigurationStore.loadImagingSessionTableConfig().getSelectedColumns();

        columnNames = new String[selectedColumns.size()];
        for (int i = 0; i < selectedColumns.size(); i++) {
            columnNames[i] = Enums.enumToString(selectedColumns.get(i));
        }

        TableData td = new TableData();
        tableData = td.generateTableData(selectedColumns);
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
        return null;
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
