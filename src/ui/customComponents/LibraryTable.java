package ui.customComponents;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;
import models.tableModels.LibraryTableModel;
import ui.corecomponents.LibraryPanel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

public class LibraryTable extends JTable {
    private final Equipment equipment;
    private final LibraryPanel libraryPanel;
    private LibraryTableModel libraryTableModel;

    public LibraryTable(Equipment equipment, List<CalibrationLibrary> calibrationLibraries, LibraryPanel libraryPanel) {
        this.equipment = equipment;
        this.libraryPanel = libraryPanel;

        createTable(calibrationLibraries);
        handleActions();
    }

    private void createTable(List<CalibrationLibrary> calibrationLibraries) {
        setModel(libraryTableModel= new LibraryTableModel(equipment, calibrationLibraries));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showHorizontalLines = true;
        setRowHeight(30);
    }

    private void handleActions() {
        getSelectionModel().addListSelectionListener(e -> libraryPanel.updateButtonState());
    }

    public LibraryTableModel getTableModel() {
        return libraryTableModel;
    }
}
