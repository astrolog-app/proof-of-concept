package controllers;

import models.equipment.Equipment;
import ui.popUps.ImagingSessionInfo;

import javax.swing.*;

public class ImagingSessionController {
    public void addImagingSessionManually(Equipment equipment) {
        new ui.popUps.NewImagingSessionManually(equipment);
    }

    public void addImagingSessionAutomatically() {

    }

    public void removeImagingSession() {
        // hardcode YES_NO_OPTION to disable translation
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");

        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this session?",
                "Delete Session Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (dialogResult == JOptionPane.YES_NO_OPTION) {
            // TODO: remove imagingSession
        }
    }

    public void editImagingSession() {

    }

    public void showImagingSessionDetails() {
        new ImagingSessionInfo();
    }
}
