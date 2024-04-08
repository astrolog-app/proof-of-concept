package ui.customComponents;

import models.imagingFrames.CalibrationFrame;
import models.equipment.Equipment;
import models.imagingFrames.ImagingFrameList;
import models.settings.AppConfig;
import models.tableModels.LibraryTableModel;
import ui.corecomponents.LibraryPanel;
import utils.FileExplorer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LibraryTable extends JTable {
    private final Equipment equipment;
    private final ImagingFrameList imagingFrameList;
    private final LibraryPanel libraryPanel;
    private final AppConfig appConfig;
    private LibraryTableModel libraryTableModel;

    public LibraryTable(Equipment equipment, ImagingFrameList imagingFrameList, LibraryPanel libraryPanel, AppConfig appConfig) {
        this.equipment = equipment;
        this.imagingFrameList = imagingFrameList;
        this.libraryPanel = libraryPanel;
        this.appConfig = appConfig;

        createTable();
        handleActions();
    }

    private void createTable() {
        setModel(libraryTableModel = new LibraryTableModel(equipment, imagingFrameList, appConfig));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showHorizontalLines = true;
        setRowHeight(30);
    }

    private void handleActions() {
        getSelectionModel().addListSelectionListener(e -> libraryPanel.updateButtonState());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    FileExplorer.openFileExplorer(appConfig.getFolderPath() + libraryTableModel.getLibraryRow(getSelectedRow()).getPath());
                }
            }
        });
    }

    public LibraryTableModel getTableModel() {
        return libraryTableModel;
    }
}
