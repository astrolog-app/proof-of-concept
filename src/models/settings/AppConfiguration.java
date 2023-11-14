package models.settings;

import models.LoggerColumns;

import java.util.List;

public class AppConfiguration {
    private AppTheme theme;
    private String folderPath;
    private List<LoggerColumns> selectedColumns;
    private NavigationBarPlacement navBarPlacement;
    private boolean enableRegularBackups;
    private boolean startInFullscreen;

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

    public NavigationBarPlacement getNavBarPlacement() {
        return navBarPlacement;
    }
    public void setNavBarPlacement(NavigationBarPlacement navBarPlacement) {
        this.navBarPlacement = navBarPlacement;
    }

    public boolean getEnableRegularBackups() {
        return enableRegularBackups;
    }
    public void setEnableRegularBackups(boolean enableRegularBackups) {
        this.enableRegularBackups = enableRegularBackups;
    }

    public boolean getStartInFullscreen() {
        return startInFullscreen;
    }
    public void setStartInFullscreen(boolean startInFullscreen) {
        this.startInFullscreen = startInFullscreen;
    }
}
