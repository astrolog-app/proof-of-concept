package services;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import models.ImagingProject;
import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;
import models.ImagingSession;
import models.imagingFrames.ImagingFrameList;
import models.license.Licence;
import models.settings.AppConfig;
import models.settings.AppTheme;
import models.settings.ImagingSessionConfig;
import services.fileHandler.*;
import services.licence.LicenceChecker;
import ui.MainUI;
import ui.startUp.StartUpPanel;
import ui.startUp.welcome.WelcomeDialogue;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AppActions {
    private static final Logger logger = AppLogger.getLogger();
    private StartUpPanel startUpPanel;
    private Licence licence;
    private AppConfig appConfig;
    private List<ImagingSession> imagingSessions = new ArrayList<>();
    private ImagingSessionConfig isConfig = new ImagingSessionConfig();
    private Equipment equipment = new Equipment();
    private List<ImagingProject> imagingProjects = new ArrayList<>();
    private ImagingFrameList imagingFrameList = new ImagingFrameList();

    /**
     * initializes the application and looks if the
     * application is started for the first time
     */
    public void initialize() {
        licence = LicenceStore.load();
        appConfig = ConfigurationStore.loadAppConfig();

        if (appConfig != null) {
            FlatDarkLaf.setup();
            startUpPanel = new StartUpPanel();

            logger.info("starting MainUI");

            loadJson();
            setApplicationTheme();
            startUpPanel.dispose();

            checkForNull();

            // start up the mainUI and check for valid licence
            SwingUtilities.invokeLater(() -> {
                MainUI mainUI = new MainUI(licence, appConfig, imagingSessions, isConfig, imagingFrameList, equipment);
                LicenceChecker licenceChecker = new LicenceChecker(licence, mainUI);

                UpdateChecker.check(appConfig);
                licenceChecker.check();
            });
        } else {
            logger.info("licence is null");
            logger.info("starting WelcomeDialogue");

            FlatLightLaf.setup();
            SwingUtilities.invokeLater(WelcomeDialogue::new);
        }
    }

    private void loadJson() {
        sleep(400);
        isConfig = ConfigurationStore.loadImagingSessionConfig();
        startUpPanel.setProgressLabel("loading configuration.json");
        startUpPanel.increaseProgress();

        sleep(100);
        equipment = EquipmentStore.load();
        startUpPanel.setProgressLabel("loading equipment.json");
        startUpPanel.increaseProgress();

        sleep(100);
        imagingSessions = ImagingSessionStore.load();
        startUpPanel.setProgressLabel("loading imagingSessions.json");
        startUpPanel.increaseProgress();

        sleep(100);
        imagingFrameList = ImagingFrameStore.load();
        startUpPanel.setProgressLabel("loading imagingFrames.json");
        startUpPanel.increaseProgress();

        sleep(100);
        imagingProjects = ImagingProjectStore.load();
        startUpPanel.setProgressLabel("loading imagingProjects.json");
        startUpPanel.increaseProgress();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            logger.severe("failed to execute Thread.sleep");
        }
    }

    private void checkForNull() {
        if (imagingSessions == null) {
            imagingSessions = new ArrayList<>();
        }

        if (equipment == null) {
            equipment = new Equipment();
        }

        if (imagingFrameList == null) {
            imagingFrameList = new ImagingFrameList();
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
        sleep(100);
        startUpPanel.setProgressLabel("set application theme");
        startUpPanel.increaseProgress();
        sleep(50);

        if (appConfig != null && appConfig.getTheme() == AppTheme.DARK) {
            FlatDarkLaf.setup();
            return;
        }

        FlatLightLaf.setup();
    }
}
