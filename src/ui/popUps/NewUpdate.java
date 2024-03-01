package ui.popUps;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.ReleaseNotesStore;
import utils.Application;

import javax.swing.*;

public class NewUpdate extends JDialog {
    private final AppConfig appConfig;
    private final ReleaseNotes releaseNotes;
    private JPanel mainPanel;
    private JButton updateNowButton;
    private JButton closeButton;
    private JLabel versionLabel;
    private JCheckBox showUpdatesCheckBox;
    private JEditorPane releaseNotesPane;

    public NewUpdate(AppConfig appConfig, ReleaseNotes releaseNotes) {
        this.appConfig = appConfig;
        this.releaseNotes = releaseNotes;

        versionLabel.setText("AstroLog V." + releaseNotes.getVersion());

        createReleaseNotesPane();

        handleActions();

        setModal(true);
        setContentPane(mainPanel);
        if (releaseNotes.getUpdated()) {
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
        releaseNotesPane.setFocusable(false);
    }

    private String buildReleaseNotesString() {
        StringBuilder str = new StringBuilder();
        str.append("<html><body>");

        if (!releaseNotes.getFeatures().isEmpty()) {
            str.append("<h1>Features:</h1>");
            str.append("<ul>");
            for (String s : releaseNotes.getFeatures()) {
                str.append("<li style=\"font-size: 1.2em;\">").append(s).append("</li>");
            }
            str.append("</ul>");
        }

        if (!releaseNotes.getBugFixes().isEmpty()) {
            str.append("<h1>Bug Fixes:</h1>");
            str.append("<ul>");
            for (String s : releaseNotes.getBugFixes()) {
                str.append("<li style=\"font-size: 1.2em;\">").append(s).append("</li>");
            }
            str.append("</ul>");
        }

        str.append("<br></br>");
        str.append("<p style=\"font-size: 1.2em;\">Release Date: ").append(releaseNotes.getReleaseDate()).append("</p>");

        str.append("</body></html>");

        return str.toString();
    }

    private void handleActions() {
        closeButton.addActionListener(e -> {
            if (releaseNotes.getUpdated()) {
                ReleaseNotesStore.delete();
            } else if (showUpdatesCheckBox.isSelected()) {
                releaseNotes.setShow(false);
                ReleaseNotesStore.save(releaseNotes, null);
            }
            dispose();
        });
        updateNowButton.addActionListener(e -> Application.update());
        showUpdatesCheckBox.addActionListener(e -> {
            ConfigurationStore.save(appConfig, null, null);
        });
    }
}
