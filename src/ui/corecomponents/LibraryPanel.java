package ui.corecomponents;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;
import models.settings.AppConfig;
import ui.customComponents.LibraryTable;
import ui.popUps.LibraryRowEditor;

import javax.swing.*;
import java.util.List;

public class LibraryPanel {
    private final Equipment equipment;
    private final List<CalibrationLibrary> library;
    private JPanel panel;
    private LibraryTable libraryTable1;
    private JButton addButton;
    private JButton editButton;

    public LibraryPanel(Equipment equipment, List<CalibrationLibrary> library, AppConfig appConfig) {
        this.equipment = equipment;
        this.library = library;

        updateButtonState();
        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> new LibraryRowEditor(null, equipment, library, libraryTable1.getTableModel()));
        editButton.addActionListener(e -> new LibraryRowEditor(libraryTable1.getTableModel().getLibraryRow(libraryTable1.getSelectedRow()),
                equipment,
                library,
                libraryTable1.getTableModel()));
    }

    public void updateButtonState() {
        boolean b = libraryTable1.getTableModel().getLibraryRow(libraryTable1.getSelectedRow()) != null;
        editButton.setEnabled(b);
    }

    private void createUIComponents() {
        libraryTable1 = new LibraryTable(equipment, library, this);
    }

    public JPanel getPanel() {
        return panel;
    }
}
