package ui.corecomponents;

import controllers.ImagingSessionController;
import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;
import models.imagingSessions.ImagingSession;
import models.settings.AppConfig;
import models.settings.ImagingSessionConfig;
import services.fileHandler.ConfigurationStore;
import ui.customComponents.ImagingSessionTable;
import utils.Images;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class LogPanel {
    private ImagingSessionController imagingSessionController;
    private final Equipment equipment;
    private final List<ImagingSession> imagingSessions;
    private final ImagingSessionConfig isConfig;
    private final List<CalibrationLibrary> calibrationLibrary;

    AppConfig appConfig;
    private JPanel panel1;
    private JLabel placeHolder1;
    private JButton automaticallyButton;
    private JButton manuallyButton;
    private JButton createLocalBackupButton;
    private JCheckBox enableRegularBackupsCheckBox;
    private JButton configureRegularBackupsButton;
    private ImagingSessionTable imagingSessionTable1;
    private JTextField searchField;
    private JButton deleteButton;
    private JButton xMarkButton;
    private JButton editButton;
    private JButton searchButton;
    private JButton saveBackupConfigButton;

    public LogPanel(AppConfig appConfig, Equipment equipment, List<ImagingSession> imagingSessions,
                    ImagingSessionConfig isConfig, List<CalibrationLibrary> calibrationLibrary) {
        this.appConfig = appConfig;
        this.equipment = equipment;
        this.imagingSessions = imagingSessions;
        this.isConfig = isConfig;
        this.calibrationLibrary = calibrationLibrary;

        updateTableButtonState();

        ImageIcon binIcon = Images.getThemeBasedIcon(appConfig, "bin", 18, 18);
        deleteButton.setIcon(binIcon);
        deleteButton.setText("");

        ImageIcon xMarkIcon = Images.getThemeBasedIcon(appConfig, "x-mark", 18, 18);
        xMarkButton.setIcon(xMarkIcon);
        xMarkButton.setText("");

        ImageIcon searchIcon = Images.getThemeBasedIcon(appConfig, "right_arrow", 18, 18);
        searchButton.setIcon(searchIcon);
        searchButton.setText("");

        handleActions();
    }

    private void handleActions() {
        enableRegularBackupsCheckBox.setSelected(appConfig.getEnableRegularBackups());

        enableRegularBackupsCheckBox.addActionListener(e -> {
            appConfig.setEnableRegularBackups(enableRegularBackupsCheckBox.isSelected());
            ConfigurationStore.save(appConfig, null,null);
        });

        manuallyButton.addActionListener(e -> imagingSessionController.addImagingSessionManually(equipment, imagingSessions, appConfig, calibrationLibrary));
        deleteButton.addActionListener(e -> imagingSessionController.removeImagingSession());
        editButton.addActionListener(e -> imagingSessionController.editImagingSession(equipment, imagingSessionTable1
                        .getTableModel()
                        .getSession(imagingSessionTable1.getSelectedRow()),
                imagingSessions,
                appConfig,
                calibrationLibrary
        ));
        xMarkButton.addActionListener(e -> {
            searchField.setText("");
            search();
        });
        searchButton.addActionListener(e -> search());

        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private void createUIComponents() {
        imagingSessionTable1 = new ImagingSessionTable(equipment, this, imagingSessions, isConfig, appConfig, calibrationLibrary);
        imagingSessionController = new ImagingSessionController(imagingSessionTable1.getTableModel(), imagingSessionTable1);
        imagingSessionTable1.setImagingSessionController(imagingSessionController);
    }

    private void search() {
        TableRowSorter<TableModel> sorter = imagingSessionTable1.getSorter();
        String searchText = searchField.getText();

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null); // If the search text is empty, show all rows
        } else {
            // Use the filter to find matching rows based on the search text
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }

//        TODO: update data in the tableModel
    }

    public void updateTableButtonState() {
        boolean b = imagingSessionTable1.getTableModel().getSession(imagingSessionTable1.getSelectedRow()) != null;
        editButton.setEnabled(b);
        deleteButton.setEnabled(b);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
