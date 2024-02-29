package ui.popUps;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.AppLogger;
import services.fileHandler.ConfigurationStore;
import utils.User;

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

        handleActions();

        setModal(true);
        setContentPane(mainPanel);
        if (afterUpdate) {
            setTitle("Release Notes");
        } else {
            setTitle("New Update Available");
        }
        setResizable(false);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
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
        // TODO: finish
        String command; // path to the executable file

        // Determine OS and set command accordingly
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            command = "path\\to\\your\\executable.exe"; // Windows path
        } else {
            command = "./path/to/your/executable"; // Linux or macOS path
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // Redirects the process's standard output and error streams to the Java process.
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Executable executed successfully");
            } else {
                System.err.println("Executable execution failed with error code: " + exitCode);
            }

            System.exit(0);
        } catch (IOException | InterruptedException e) {
            logger.severe("couldn't update application:" + "\t" + e.getMessage());
        }
    }
}
