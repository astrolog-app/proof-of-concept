package ui.corecomponents;

import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;
import models.settings.AppConfig;
import ui.customComponents.LibraryTable;
import ui.popUps.rowEditors.LibraryRowEditor;

import javax.swing.*;
import java.util.List;

public class LibraryPanel {
    private final Equipment equipment;
    private final List<CalibrationFrame> library;
    private final AppConfig appConfig;
    private JPanel panel;
    private LibraryTable libraryTable1;
    private JButton addButton;
    private JButton editButton;

    public LibraryPanel(Equipment equipment, List<CalibrationFrame> library, AppConfig appConfig) {
        this.equipment = equipment;
        this.library = library;
        this.appConfig = appConfig;

        updateButtonState();
        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> new LibraryRowEditor(null, equipment, library, libraryTable1.getTableModel(), appConfig, null));
        editButton.addActionListener(e -> new LibraryRowEditor(libraryTable1.getTableModel().getLibraryRow(libraryTable1.getSelectedRow()),
                equipment,
                library,
                libraryTable1.getTableModel(),
                appConfig,
                null));
    }

    public void updateButtonState() {
        boolean b = libraryTable1.getTableModel().getLibraryRow(libraryTable1.getSelectedRow()) != null;
        editButton.setEnabled(b);
    }

    private void createUIComponents() {
        libraryTable1 = new LibraryTable(equipment, library, this, appConfig);
    }

    public JPanel getPanel() {
        return panel;
    }
}
