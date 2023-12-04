package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.settings.AppConfiguration;
import models.settings.AppTheme;
import models.LoggerColumns;
import models.settings.NavigationBarPlacement;
import services.fileHandler.ConfigurationStore;
import ui.MainUI;

import java.util.ArrayList;
import java.util.List;

public class AppActions {
    private final AppConfiguration appConfig = new AppConfiguration();

    public void initialize() {
        try {
            ConfigurationStore.load(appConfig);
            setApplicationTheme();
            new MainUI(appConfig);
        } catch (Exception e) {
            appConfig.setTheme(AppTheme.LIGHT);
            appConfig.setFolderPath("path");
            List<LoggerColumns> list = new ArrayList<>();
            list.add(LoggerColumns.DATE);
            list.add(LoggerColumns.TARGET);
            appConfig.setSelectedColumns(list);
            appConfig.setNavBarPlacement(NavigationBarPlacement.LEFT);
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
