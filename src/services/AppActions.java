package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.settings.AppConfig;
import models.settings.AppTheme;
import services.fileHandler.ConfigurationStore;
import ui.MainUI;
import ui.startUp.welcome.WelcomeDialogue;

import javax.swing.*;
import java.util.logging.Logger;

public class AppActions {
    private static final Logger logger = AppLogger.getLogger();
    private final AppConfig appConfig;

    public AppActions() {
        appConfig = ConfigurationStore.loadAppConfig();
    }

    /**
     * initializes the application and looks if the
     * application is started for the first time
     */
    public void initialize() {
        setApplicationTheme();

        if (appConfig != null) {
            logger.info("starting MainUI");

            SwingUtilities.invokeLater(() -> new MainUI(appConfig));
        } else {
            logger.info("appConfig is null");
            logger.info("starting WelcomeDialogue");

            SwingUtilities.invokeLater(WelcomeDialogue::new);
        }
    }

    public static void restart() {
        String java = System.getProperty("java.home") + "/bin/java";
        String className = "App";

        ProcessBuilder builder = new ProcessBuilder(java, "-cp", System.getProperty("java.class.path"), className);

        try {
            builder.start();
            logger.info("restarted application");
        } catch (Exception e) {
            logger.severe("couldn't restart application:" + "\t" + e.getMessage());
        }
        System.exit(0);
    }

    /**
     * sets the application theme based on the config file;
     * if it doesn't have a theme defined it sets it to light mode
     */
    private void setApplicationTheme() {
        if (appConfig != null) {
            if (appConfig.getTheme() == AppTheme.DARK) {
                FlatDarkLaf.setup();
            } else {
                FlatLightLaf.setup();
            }
        } else {
            FlatLightLaf.setup();
        }
    }
}
