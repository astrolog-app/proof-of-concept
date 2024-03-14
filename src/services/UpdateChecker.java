package services;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.fileHandler.ReleaseNotesStore;
import ui.popUps.NewUpdate;
import utils.FileSystem;
import utils.Paths;

import javax.swing.*;

public class UpdateChecker {
    /**
     * downloads a releaseNotes file, saves it in the cache folder, and overwrites the existing one
     * if the downloaded one is from a newer release
     */
    public static void lookForNewUpdates() {
        ReleaseNotes releaseNotes = ReleaseNotesStore.load(null);
        String newestVersion = null;

        R2.downloadLatestReleaseNotes();
        ReleaseNotes newReleaseNotes = ReleaseNotesStore.load(Paths.CACHE_PATH);
        if (newReleaseNotes != null) {
            newestVersion = newReleaseNotes.getVersion();
        }

        if (releaseNotes == null || (newestVersion != null && !releaseNotes.getVersion().equals(newestVersion))) {
            FileSystem.copyFile("releaseNotes.json", Paths.CACHE_PATH, Paths.DATA_PATH);
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
