package ui.popUps;

import models.settings.AppConfig;
import services.fileHandler.ConfigurationStore;
import utils.User;

import javax.swing.*;
import java.util.List;

public class NewUpdate extends JDialog {
    private final AppConfig appConfig;
    private final List<String> releaseNotes;
    private JPanel mainPanel;
    private JButton updateNowButton;
    private JButton closeButton;
    private JLabel versionLabel;
    private JCheckBox showUpdatesCheckBox;

    public NewUpdate(AppConfig appConfig, String version, List<String> releaseNotes) {
        this.appConfig = appConfig;
        this.releaseNotes = releaseNotes;

        versionLabel.setText("AstroLog V " + version);

        handleActions();

        setModal(true);
        setContentPane(mainPanel);
        setTitle("New Update Available");
        setResizable(false);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleActions() {
        closeButton.addActionListener(e -> dispose());
        updateNowButton.addActionListener(e -> User.openLink("https://www.google.com"));
        showUpdatesCheckBox.addActionListener(e -> {
            appConfig.setShowUpdates(!showUpdatesCheckBox.isSelected());
            ConfigurationStore.save(appConfig, null, null);
        });
    }
}
