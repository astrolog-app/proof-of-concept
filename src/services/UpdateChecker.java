package services;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.fileHandler.ReleaseNotesStore;
import ui.popUps.NewUpdate;
import utils.Application;

import javax.swing.*;
import java.util.Objects;

public class UpdateChecker {
    public static void fetch() {
        ReleaseNotes releaseNotes = ReleaseNotesStore.load();
        String newestVersion = GitHubAPI.getNewestVersion();

        if (!Application.VERSION.equals(newestVersion)) {
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
