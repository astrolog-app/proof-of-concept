package models;

import services.fileHandler.FileLoader;

import java.util.List;

public class AppConfiguration {
    private AppTheme theme;
    private String folderPath;
    private List<LoggerColumns> selectedColumns;

    public AppConfiguration() {
        FileLoader appConfigLoader = new FileLoader();
        theme = appConfigLoader.getTheme();
        folderPath = appConfigLoader.getFolderPath();
        selectedColumns = appConfigLoader.getSelectedColumns();
    }

    public AppTheme getTheme() {
        return theme;
    }
    public void setTheme(AppTheme theme) {
        this.theme = theme;
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
