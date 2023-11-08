package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.AppConfiguration;
import models.ApplicationTheme;
import ui.MainUI;

public class ApplicationActions {
    private final AppConfiguration appConfig;

    public ApplicationActions(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public void initialize() {
        try {
            setApplicationTheme();
            new MainUI(appConfig);
        } catch (Exception e) {
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
        if (appConfig.getTheme() == ApplicationTheme.DARK) {
            FlatDarkLaf.setup();
        } else if (appConfig.getTheme() == ApplicationTheme.LIGHT){
            FlatLightLaf.setup();
        } else {
            FlatLightLaf.setup();
            throw new Exception();
        }
    }
}
