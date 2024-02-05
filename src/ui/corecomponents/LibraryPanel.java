package ui.corecomponents;

import models.equipment.Equipment;
import services.fileHandler.CalibrationLibraryStore;
import ui.customComponents.LibraryTable;
import ui.popUps.LibraryRowEditor;

import javax.swing.*;

public class LibraryPanel {
    private final Equipment equipment;
    private JPanel panel;
    private LibraryTable libraryTable1;
    private JButton addButton;
    private JButton editButton;

    public LibraryPanel(Equipment equipment) {
        this.equipment = equipment;

        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> {
            new LibraryRowEditor(null, equipment);
        });
    }

    private void createUIComponents() {
        libraryTable1 = new LibraryTable(equipment, CalibrationLibraryStore.load());
    }

    public JPanel getPanel() {
        return panel;
    }
}
