package ui.popUps;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.AppLogger;
import services.fileHandler.ConfigurationStore;
import utils.Paths;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Logger;

public class NewUpdate extends JDialog {
    private static final Logger logger = AppLogger.getLogger();
    private final AppConfig appConfig;
    private final ReleaseNotes releaseNotes;
    private JPanel mainPanel;
    private JButton updateNowButton;
    private JButton closeButton;
    private JLabel versionLabel;
    private JCheckBox showUpdatesCheckBox;
    private JEditorPane releaseNotesPane;

    public NewUpdate(AppConfig appConfig, ReleaseNotes releaseNotes, boolean afterUpdate) {
        this.appConfig = appConfig;
        this.releaseNotes = releaseNotes;

        versionLabel.setText("AstroLog V " + releaseNotes.getVersion());

        createReleaseNotesPane();

        handleActions();

        setModal(true);
        setContentPane(mainPanel);
        if (afterUpdate) {
            setTitle("Release Notes");
        } else {
            setTitle("New Update Available");
        }
        setResizable(false);
        setSize(450, 575);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createReleaseNotesPane() {
        releaseNotesPane.setContentType("text/html");
        releaseNotesPane.setText(buildReleaseNotesString());

        releaseNotesPane.setEditable(false);
    }

    private String buildReleaseNotesString() {
        StringBuilder str = new StringBuilder();
        str.append("<html><body>");

        str.append("<h1>Features:</h1>");
        str.append("<ul>");
        for (String s : releaseNotes.getFeatures()) {
            str.append("<li style=\"font-size: 1.2em;\">").append(s).append("</li>");
        }
        str.append("</ul>");

        str.append("<h1>Bug Fixes:</h1>");
        str.append("<ul>");
        for (String s : releaseNotes.getBugFixes()) {
            str.append("<li style=\"font-size: 1.2em;\">").append(s).append("</li>");
        }
        str.append("</ul>");

        str.append("<br></br>");
        str.append("<p style=\"font-size: 1.2em;\">Release Date: ").append(releaseNotes.getReleaseDate()).append("</p>");

        str.append("</body></html>");

        return str.toString();
    }

    private void handleActions() {
        closeButton.addActionListener(e -> dispose());
        updateNowButton.addActionListener(e -> updateApp());
        showUpdatesCheckBox.addActionListener(e -> {
            appConfig.setShowUpdates(!showUpdatesCheckBox.isSelected());
            ConfigurationStore.save(appConfig, null, null);
        });
    }

    private void updateApp() {
        String command; // path to the executable file

        // Determine OS and set command accordingly
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            command = Paths.PROJECT_PATH + "update.exe"; // Windows path
        } else {
            command = Paths.PROJECT_PATH + "update"; // Linux or macOS path
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // Redirects the process's standard output and error streams to the Java process.
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Executable executed successfully");
            } else {
                logger.severe("Executable execution failed with error code: " + exitCode);
            }

            System.exit(0);
        } catch (IOException | InterruptedException e) {
            logger.severe("couldn't update application:" + "\t" + e.getMessage());
        }
    }
}
