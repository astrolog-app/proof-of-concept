package ui.customComponents;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;
import models.tableModels.LibraryTableModel;

import javax.swing.*;
import java.util.List;

public class LibraryTable extends JTable {
    private final Equipment equipment;

    public LibraryTable(Equipment equipment, List<CalibrationLibrary> calibrationLibraries) {
        this.equipment = equipment;

        createTable(calibrationLibraries);
    }

    private void createTable(List<CalibrationLibrary> calibrationLibraries) {
        setModel(new LibraryTableModel(equipment, calibrationLibraries));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showHorizontalLines = true;
    }
}
