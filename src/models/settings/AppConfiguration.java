package models.settings;

import java.util.HashMap;
import java.util.List;

public class AppConfiguration {
    private AppTheme theme;
    private String folderPath;
    private NavigationBarPlacement navigationBarPlacement;
    private boolean enableRegularBackups;
    private boolean startInFullscreen;
    private List<LoggerColumns> selectedColumns;
    private HashMap<LoggerColumns, Integer> columnsSize;

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

    public NavigationBarPlacement getNavigationBarPlacement() {
        return navigationBarPlacement;
    }
    public void setNavigationBarPlacement(NavigationBarPlacement navigationBarPlacement) {
        this.navigationBarPlacement = navigationBarPlacement;
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
