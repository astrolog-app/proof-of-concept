package services;

import models.ReleaseNotes;
import models.settings.AppConfig;
import ui.popUps.NewUpdate;

import javax.swing.*;

public class UpdateChecker {
    public static void fetch() {}

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
