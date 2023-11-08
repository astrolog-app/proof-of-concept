package models;

import services.fileHandler.ConfigJsonFileHandler;

import java.util.List;

public class AppConfiguration {
    private final ApplicationTheme theme;
    private String folderPath;
    private List<LoggerColumns> selectedColumns;

    public AppConfiguration() {
        ConfigJsonFileHandler appConfigLoader = new ConfigJsonFileHandler();
        theme = appConfigLoader.getTheme();
        folderPath = appConfigLoader.getFolderPath();
        selectedColumns = appConfigLoader.getSelectedColumns();
    }

    public ApplicationTheme getTheme() {
        return theme;
    }

    public String getFolderPath() {
        return folderPath;
    }
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public List<LoggerColumns> getSelectedColumns() {
        return selectedColumns;
    }
    public void setSelectedColumns(List<LoggerColumns> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }
}
