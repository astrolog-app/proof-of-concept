package models.settings;

public class AppConfig {
    private AppTheme theme;
    private String folderPath;
    private NavigationBarPlacement navigationBarPlacement;
    private boolean enableRegularBackups;
    private boolean startInFullscreen;
    private boolean showUpdates;

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

    public boolean getShowUpdates() {
        return showUpdates;
    }
    public void setShowUpdates(boolean showUpdates) {
        this.showUpdates = showUpdates;
    }
}
