package ui.corecomponents;

import controllers.ImagingSessionController;
import models.equipment.Equipment;
import models.imagingSessions.ImagingSession;
import models.settings.AppConfig;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.ImagingSessionStore;
import ui.customComponents.ImagingSessionTable;
import utils.Images;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class LogPanel {
    private ImagingSessionController imagingSessionController;
    private final Equipment equipment;
    private List<ImagingSession> imagingSessions;

    AppConfig appConfig;
    private JPanel panel1;
    private JLabel placeHolder1;
    private JButton fromExistingFolderButton;
    private JButton manuallyButton;
    private JButton createLocalBackupButton;
    private JCheckBox enableRegularBackupsCheckBox;
    private JButton configureRegularBackupsButton;
    private ImagingSessionTable imagingSessionTable1;
    private JTextField searchField;
    private JButton deleteButton;
    private JButton detailsButton;
    private JButton xMarklButton;
    private JButton editButton;
    private JButton imagingSessionSettings;
    private JButton searchButton;
    private JButton saveBackupConfigButton;

    public LogPanel(AppConfig appConfig, Equipment equipment) {
        this.appConfig = appConfig;
        this.equipment = equipment;

        updateTableButtonState();

        ImageIcon binIcon = Images.getThemeBasedIcon(appConfig, "bin", 18, 18);
        deleteButton.setIcon(binIcon);
        deleteButton.setText("");

        ImageIcon xMarkIcon = Images.getThemeBasedIcon(appConfig, "x-mark", 18, 18);
        xMarklButton.setIcon(xMarkIcon);
        xMarklButton.setText("");

        ImageIcon settingsIcon = Images.getThemeBasedIcon(appConfig, "settings", 18, 18);
        imagingSessionSettings.setIcon(settingsIcon);
        imagingSessionSettings.setText("");

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

        manuallyButton.addActionListener(e -> imagingSessionController.addImagingSessionManually(equipment, imagingSessions));
        detailsButton.addActionListener(e -> imagingSessionController
                .showImagingSessionDetails(
                        imagingSessionTable1.getTableModel().getSession(imagingSessionTable1.getSelectedRow())
                ));
        deleteButton.addActionListener(e -> imagingSessionController.removeImagingSession());
        editButton.addActionListener(e -> imagingSessionController.editImagingSession(equipment, imagingSessionTable1
                        .getTableModel()
                        .getSession(imagingSessionTable1.getSelectedRow()),
                imagingSessions
        ));
        xMarklButton.addActionListener(e -> {
            searchField.setText("");
            search();
        });
        searchButton.addActionListener(e -> search());
    }

    private void createUIComponents() {
        imagingSessions = ImagingSessionStore.loadImagingSessions();

        imagingSessionTable1 = new ImagingSessionTable(equipment, this, imagingSessions);
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
        detailsButton.setEnabled(b);
        editButton.setEnabled(b);
        deleteButton.setEnabled(b);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
