package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.AppConfiguration;
import models.AppTheme;
import models.LoggerColumns;
import services.fileHandler.ConfigurationStore;
import ui.MainUI;
import utils.Paths;

import java.util.ArrayList;
import java.util.List;

public class AppActions {
    private final AppConfiguration appConfig = new AppConfiguration();
    private final ConfigurationStore configStore = new ConfigurationStore(appConfig);

    public void initialize() {
        try {
            configStore.load();
            setApplicationTheme();
            new MainUI(appConfig, configStore);
        } catch (Exception e) {
            appConfig.setTheme(AppTheme.LIGHT);
            appConfig.setFolderPath("path");
            List<LoggerColumns> list = new ArrayList<>();
            list.add(LoggerColumns.DATE);
            list.add(LoggerColumns.TARGET);
            appConfig.setSelectedColumns(list);
            appConfig.setStartInFullscreen(false);

            configStore.save();
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
