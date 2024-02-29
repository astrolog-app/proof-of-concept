package services;

import models.settings.AppConfig;
import services.fileHandler.ReleaseNotesStore;
import ui.popUps.NewUpdate;

import javax.swing.*;

public class UpdateChecker {
    public static void check(AppConfig appConfig) {
        SwingUtilities.invokeLater(() -> new NewUpdate(appConfig, ReleaseNotesStore.load(), true));
    }
}
