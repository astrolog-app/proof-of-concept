package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.settings.AppConfig;
import models.settings.AppTheme;
import services.fileHandler.ConfigurationStore;
import ui.MainUI;
import ui.startUp.welcome.WelcomeDialogue;
import java.util.logging.Logger;

public class AppActions {
    private static final Logger logger = AppLogger.getLogger();
    private final AppConfig appConfig = ConfigurationStore.loadAppConfig();

    public void initialize() {
        assert appConfig != null;

        try {
            setApplicationTheme();

            logger.info("starting MainUI");

            new MainUI(appConfig);
        } catch (Exception e) {
            logger.warning("couldn't find config file:" + "\t" + e.getMessage());

            logger.info("starting WelcomeDialogue");
            FlatLightLaf.setup();
            new WelcomeDialogue();
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
