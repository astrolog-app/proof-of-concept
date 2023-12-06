package models.settings;

import java.util.HashMap;
import java.util.List;

public class ImagingSessionConfig {
    private List<LoggerColumns> selectedColumns;
    private HashMap<LoggerColumns, Integer> columnsSize;

    public List<LoggerColumns> getSelectedColumns() {
        return selectedColumns;
    }
    public void setSelectedColumns(List<LoggerColumns> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }

    public HashMap<LoggerColumns, Integer> getColumnsSize() {
        return columnsSize;
    }
    public void setColumnsSize(HashMap<LoggerColumns, Integer> columnsSize) {
        this.columnsSize = columnsSize;
    }
}
