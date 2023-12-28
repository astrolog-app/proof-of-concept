package controllers;

import models.equipment.Equipment;
import models.imagingSessionTable.ImagingSessionTableModel;
import models.imagingSessions.ImagingSession;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.ImagingSessionStore;
import ui.customComponents.ImagingSessionTable;
import ui.popUps.ImagingSessionInfo;
import ui.popUps.NewImagingSessionManually;

import javax.swing.*;
import java.util.List;

public class ImagingSessionController {
    private final ImagingSessionTableModel isTableModel;
    private final ImagingSessionTable imagingSessionTable;

    public ImagingSessionController(ImagingSessionTableModel isTableModel, ImagingSessionTable imagingSessionTable) {
        this.isTableModel = isTableModel;
        this.imagingSessionTable = imagingSessionTable;
    }

    public void addImagingSessionManually(Equipment equipment, List<ImagingSession> imagingSessions) {
        new NewImagingSessionManually(equipment, null, isTableModel, imagingSessions);
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

    public void editImagingSession(Equipment equipment, ImagingSession session, List<ImagingSession> imagingSessions) {
        NewImagingSessionManually n = new NewImagingSessionManually(equipment, session, isTableModel, imagingSessions);
    }

    public void showImagingSessionDetails(ImagingSession session) {
        new ImagingSessionInfo(session);
    }
}
