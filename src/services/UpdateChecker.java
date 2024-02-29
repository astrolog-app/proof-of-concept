package services;

import models.ReleaseNotes;
import models.settings.AppConfig;
import services.fileHandler.ReleaseNotesStore;
import ui.popUps.NewUpdate;

import javax.swing.*;

public class UpdateChecker {
    public static void check(AppConfig appConfig) {
        if (appConfig.getCheckForUpdates()) {
            ReleaseNotes r = ReleaseNotesStore.load();

            if (r != null && r.getShow()) {
                SwingUtilities.invokeLater(() -> {
                    Timer timer = new Timer(300, e -> new NewUpdate(appConfig, r));

                    timer.setRepeats(false);
                    timer.start();
                });
            }
        }
    }
}
