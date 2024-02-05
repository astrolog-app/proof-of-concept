package ui.corecomponents;

import models.equipment.Equipment;
import models.equipment.EquipmentType;
import services.fileHandler.CalibrationLibraryStore;
import ui.customComponents.ImagingSessionTable;
import ui.customComponents.LibraryTable;
import ui.customComponents.equipmentTable.EquipmentTableWrapper;

import javax.swing.*;

public class LibraryPanel {
    private final Equipment equipment;
    private JPanel panel;
    private LibraryTable libraryTable1;

    public LibraryPanel(Equipment equipment) {
        this.equipment = equipment;
    }

    private void createUIComponents() {
        libraryTable1 = new LibraryTable(equipment, CalibrationLibraryStore.load());
    }

    public JPanel getPanel() {
        return panel;
    }
}
