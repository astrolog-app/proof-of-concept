package controllers;

import models.equipment.Equipment;
import models.imagingSessionTable.ImagingSessionTableModel;
import models.imagingSessions.ImagingSession;
import ui.customComponents.ImagingSessionTable;
import ui.popUps.ImagingSessionInfo;
import ui.popUps.NewImagingSessionManually;

import javax.swing.*;

public class ImagingSessionController {
    private final ImagingSessionTableModel isTableModel;
    private final ImagingSessionTable imagingSessionTable;

    public ImagingSessionController(ImagingSessionTableModel isTableModel, ImagingSessionTable imagingSessionTable) {
        this.isTableModel = isTableModel;
        this.imagingSessionTable = imagingSessionTable;
    }

    public void addImagingSessionManually(Equipment equipment) {
        new NewImagingSessionManually(equipment, true);
        // TODO: update sorting
    }

    public void addImagingSessionAutomatically() {

    }

    public void removeImagingSession() {
        // hardcode YES_NO_OPTION to disable translation
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");

        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this session?",
                "Delete Session Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (dialogResult == JOptionPane.YES_NO_OPTION) {
            isTableModel.removeSession(isTableModel.getSession(imagingSessionTable.getSelectedRow()));
        }
    }

    public void editImagingSession(Equipment equipment) {
        new NewImagingSessionManually(equipment, false);
    }

    public void showImagingSessionDetails(ImagingSession session) {
        new ImagingSessionInfo(session);
    }
}
