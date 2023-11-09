package models;

import services.fileHandler.ConfigurationLoader;

import java.util.List;

public class AppConfiguration {
    private AppTheme theme;
    private String folderPath;
    private List<LoggerColumns> selectedColumns;
    private boolean startInFullscreen;

    public AppConfiguration() {
        ConfigurationLoader appConfigLoader = new ConfigurationLoader();
        theme = appConfigLoader.getTheme();
        folderPath = appConfigLoader.getFolderPath();
        selectedColumns = appConfigLoader.getSelectedColumns();
        startInFullscreen = appConfigLoader.getStartInFullscreen();
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

    public boolean getStartInFullscreen() {
        return startInFullscreen;
    }
    public void setStartInFullscreen(boolean startInFullscreen) {
        this.startInFullscreen = startInFullscreen;
    }
}
