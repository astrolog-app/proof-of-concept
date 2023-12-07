package models.settings;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class ImagingSessionConfig {
    private List<LoggerColumns> selectedColumns;
    private HashMap<LoggerColumns, Integer> columnsSize;
    private LoggerColumns defaultSortedColumn;
    private SortOrder columnSortingType;

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

    public LoggerColumns getDefaultSortedColumn() {
        return defaultSortedColumn;
    }
    public void setDefaultSortedColumn(LoggerColumns defaultSortedColumn) {
        this.defaultSortedColumn = defaultSortedColumn;
    }

    public SortOrder getColumnSortingType() {
        return columnSortingType;
    }
    public void setColumnSortingType(SortOrder columnSortingType) {
        this.columnSortingType = columnSortingType;
    }
}
