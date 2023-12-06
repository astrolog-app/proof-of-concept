package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.settings.AppConfiguration;
import models.settings.AppTheme;
import models.settings.LoggerColumns;
import models.settings.NavigationBarPlacement;
import services.fileHandler.ConfigurationStore;
import ui.MainUI;

import java.util.ArrayList;
import java.util.List;

public class AppActions {
    private final AppConfiguration appConfig = ConfigurationStore.loadAppConfig();

    public void initialize() {
        assert appConfig != null;

        try {
            setApplicationTheme();
            new MainUI(appConfig);
        } catch (Exception e) {
            e.printStackTrace();
            appConfig.setTheme(AppTheme.LIGHT);
            appConfig.setFolderPath("path");
            List<LoggerColumns> list = new ArrayList<>();
            list.add(LoggerColumns.DATE);
            list.add(LoggerColumns.TARGET);
            appConfig.setSelectedColumns(list);
            appConfig.setNavigationBarPlacement(NavigationBarPlacement.LEFT);
            appConfig.setEnableRegularBackups(true);
            appConfig.setStartInFullscreen(false);

            ConfigurationStore.save( appConfig, null);
            AppActions.restart();

            //new WelcomePanel();
        }
    }

    public static void restart() {
        String java = System.getProperty("java.home") + "/bin/java";
        String className = "App";

        ProcessBuilder builder = new ProcessBuilder(java, "-cp", System.getProperty("java.class.path"), className);

        try {
            builder.start();
        } catch (Exception e) {
            System.out.println("Couldn't restart Application!");
        }
        System.exit(0);
    }

    private void setApplicationTheme() throws Exception {
        assert appConfig != null;

        if (appConfig.getTheme() == AppTheme.DARK) {
            FlatDarkLaf.setup();
        } else if (appConfig.getTheme() == AppTheme.LIGHT){
            FlatLightLaf.setup();
        } else {
            FlatLightLaf.setup();
            throw new Exception();
        }
    }
}
