package ui.corecomponents;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;
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

    public LibraryPanel(Equipment equipment, List<CalibrationLibrary> library) {
        this.equipment = equipment;
        this.library = library;

        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> {
            new LibraryRowEditor(null, equipment);
        });
    }

    private void createUIComponents() {
        libraryTable1 = new LibraryTable(equipment, library);
    }

    public JPanel getPanel() {
        return panel;
    }
}
