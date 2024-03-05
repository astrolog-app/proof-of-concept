package services;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.fileHandler.ReleaseNotesStore;
import ui.popUps.NewUpdate;

import javax.swing.*;

public class UpdateChecker {
    public static void fetch() {
        ReleaseNotes releaseNotes = ReleaseNotesStore.load();
        String newestVersion = GitHubAPI.getNewestVersion();

        // if the release notes are not present or there are newer release notes, the app downloads the newest release notes
        if (releaseNotes == null || !releaseNotes.getVersion().equals(newestVersion)) {
            GitHubAPI.downloadFile(newestVersion, "releaseNotes.json");
        }
    }

    public static void showNewUpdates(AppConfig appConfig, ReleaseNotes releaseNotes) {
        if (releaseNotes != null && releaseNotes.getShow()) {
            SwingUtilities.invokeLater(() -> {
                Timer timer = new Timer(300, e -> new NewUpdate(appConfig, releaseNotes));

                timer.setRepeats(false);
                timer.start();
            });
        }
    }
}
