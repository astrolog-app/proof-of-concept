package services;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.fileHandler.ReleaseNotesStore;
import ui.popUps.NewUpdate;

import javax.swing.*;

public class UpdateChecker {
    /**
     * fetches the GitHubAPI and looks for the most recent update and downloads the releaseNotes.json
     * if a new update is available
     */
    public static void lookForNewUpdates() {
        ReleaseNotes releaseNotes = ReleaseNotesStore.load();
        String newestVersion = GitHubAPI.getNewestVersion();

        // if the release notes are not present or there are newer release notes, the app downloads the newest release notes
        if (releaseNotes == null || (newestVersion != null && !releaseNotes.getVersion().equals(newestVersion))) {
            GitHubAPI.downloadFile(newestVersion, "releaseNotes.json");
        }
    }

    /**
     * if a new update is present it displays the releaseNotes.json in a new popup that lets you update AstroLog
     *
     * @param appConfig the appConfig of AstroLog
     * @param releaseNotes the loaded releaseNotes.json of the new update
     */
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
