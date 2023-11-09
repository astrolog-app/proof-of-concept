package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.AppConfiguration;
import models.AppTheme;
import models.LoggerColumns;
import services.fileHandler.ConfigurationSaver;
import ui.MainUI;

import java.util.ArrayList;
import java.util.List;

public class AppActions {
    private final AppConfiguration appConfig;

    public AppActions(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public void initialize() {
        try {
            setApplicationTheme();
            new MainUI(appConfig);
        } catch (Exception e) {
            appConfig.setTheme(AppTheme.LIGHT);
            appConfig.setFolderPath("path");
            List<LoggerColumns> list = new ArrayList<>();
            list.add(LoggerColumns.DATE);
            list.add(LoggerColumns.OBJECT);
            appConfig.setSelectedColumns(list);
            appConfig.setStartInFullscreen(false);

            ConfigurationSaver configurationSaver = new ConfigurationSaver();
            configurationSaver.saveAppConfig(appConfig);
            AppActions.restart();

            //new WelcomePanel();
        }
    }

    public static void restart() {
        String java = System.getProperty("java.home") + "/bin/java";
        String className = "Application";

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
