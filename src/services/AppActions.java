package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.settings.AppConfig;
import models.settings.AppTheme;
import models.settings.LoggerColumns;
import models.settings.NavigationBarPlacement;
import services.fileHandler.ConfigurationStore;
import ui.MainUI;
import utils.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppActions {
    private final Logger logger = AppLogger.getLogger();
    private final AppConfig appConfig = ConfigurationStore.loadAppConfig();

    public void initialize() {
        assert appConfig != null;

        try {
            setApplicationTheme();

            logger.fine("Starting MainUI");

            new MainUI(appConfig);
        } catch (Exception e) {
            logger.severe("Couldn't restart application!" + "\t" + e.getMessage());
            System.out.println(e.getMessage());

            appConfig.setTheme(AppTheme.LIGHT);
            appConfig.setFolderPath("path");
            List<LoggerColumns> list = new ArrayList<>();
            list.add(LoggerColumns.DATE);
            list.add(LoggerColumns.TARGET);
            appConfig.setNavigationBarPlacement(NavigationBarPlacement.LEFT);
            appConfig.setEnableRegularBackups(true);
            appConfig.setStartInFullscreen(false);

            //ConfigurationStore.save( appConfig, null);
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
