package ui.corecomponents;

import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;
import models.imagingFrames.ImagingFrameList;
import models.settings.AppConfig;
import ui.customComponents.LibraryTable;
import ui.popUps.rowEditors.LibraryRowEditor;

import javax.swing.*;
import java.util.List;

public class LibraryPanel {
    private final Equipment equipment;
    private final ImagingFrameList imagingFrameList;
    private final AppConfig appConfig;
    private JPanel panel;
    private LibraryTable libraryTable1;
    private JButton addButton;
    private JButton editButton;

    public LibraryPanel(Equipment equipment, ImagingFrameList imagingFrameList, AppConfig appConfig) {
        this.equipment = equipment;
        this.imagingFrameList = imagingFrameList;
        this.appConfig = appConfig;

        updateButtonState();
        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new LibraryRowEditor(null, equipment, imagingFrameList, libraryTable1.getTableModel(), appConfig, null)));
        editButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new LibraryRowEditor(libraryTable1.getTableModel().getLibraryRow(libraryTable1.getSelectedRow()),
                equipment,
                imagingFrameList,
                libraryTable1.getTableModel(),
                appConfig,
                null)));
    }

    public void updateButtonState() {
        boolean b = libraryTable1.getTableModel().getLibraryRow(libraryTable1.getSelectedRow()) != null;
        editButton.setEnabled(b);
    }

    private void createUIComponents() {
        libraryTable1 = new LibraryTable(equipment, imagingFrameList, this, appConfig);
    }

    public JPanel getPanel() {
        return panel;
    }
}
