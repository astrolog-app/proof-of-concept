package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.AppConfiguration;
import models.ApplicationTheme;
import models.LoggerColumns;
import services.fileHandler.FileSaver;
import ui.MainUI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
            appConfig.setTheme(ApplicationTheme.LIGHT);
            appConfig.setFolderPath("path");
            List<LoggerColumns> list = new ArrayList<>();
            list.add(LoggerColumns.DATE);
            list.add(LoggerColumns.OBJECT);
            appConfig.setSelectedColumns(list);

            FileSaver fileSaver = new FileSaver();
            fileSaver.saveAppConfig(appConfig);
            ApplicationActions.restart();

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
