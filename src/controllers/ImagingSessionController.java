package controllers;

import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;
import models.imagingFrames.ImagingFrameList;
import models.settings.AppConfig;
import models.tableModels.ImagingSessionTableModel;
import models.ImagingSession;
import ui.customComponents.ImagingSessionTable;
import ui.popUps.NewBrand;
import ui.popUps.rowEditors.ImagingSessionRowEditor;

import javax.swing.*;
import java.util.List;

public class ImagingSessionController {
    private final ImagingSessionTableModel isTableModel;
    private final ImagingSessionTable imagingSessionTable;

    public ImagingSessionController(ImagingSessionTableModel isTableModel, ImagingSessionTable imagingSessionTable) {
        this.isTableModel = isTableModel;
        this.imagingSessionTable = imagingSessionTable;
    }

    public void addImagingSessionManually(Equipment equipment, List<ImagingSession> imagingSessions, AppConfig appConfig, ImagingFrameList imagingFrameList) {

        SwingUtilities.invokeLater(() -> new ImagingSessionRowEditor(equipment, null, isTableModel, imagingSessions, appConfig, imagingFrameList));
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

    public void editImagingSession(Equipment equipment, ImagingSession session, List<ImagingSession> imagingSessions, AppConfig appConfig, ImagingFrameList imagingFrameList) {
        SwingUtilities.invokeLater(() -> new ImagingSessionRowEditor(equipment, session, isTableModel, imagingSessions, appConfig, imagingFrameList));

    }
}
