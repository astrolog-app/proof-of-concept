package ui.corecomponents;

import controllers.ImagingSessionController;
import models.equipment.Equipment;
import models.settings.AppConfig;
import services.fileHandler.ConfigurationStore;
import ui.customComponents.ImagingSessionTable;
import utils.Images;

import javax.swing.*;

public class LogPanel {
    ImagingSessionController imagingSessionController;
    Equipment equipment;

    AppConfig appConfig;
    private JPanel panel1;
    private JLabel placeHolder1;
    private JButton fromExistingFolderButton;
    private JButton manuallyButton;
    private JButton createLocalBackupButton;
    private JCheckBox enableRegularBackupsCheckBox;
    private JButton configureRegularBackupsButton;
    private ImagingSessionTable imagingSessionTable1;
    private JTextField textField1;
    private JButton deleteButton;
    private JButton detailsButton;
    private JButton xMarklButton;
    private JButton editButton;
    private JButton imagingSessionSettings;
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

        backupHandler();

        manuallyButton.addActionListener(e -> imagingSessionController.addImagingSessionManually(equipment));
        detailsButton.addActionListener(e -> imagingSessionController.showImagingSessionDetails());
        deleteButton.addActionListener(e -> imagingSessionController.removeImagingSession());
        editButton.addActionListener(e -> imagingSessionController.editImagingSession());
    }

    private void backupHandler() {
        enableRegularBackupsCheckBox.setSelected(appConfig.getEnableRegularBackups());

        enableRegularBackupsCheckBox.addActionListener(e -> {
            appConfig.setEnableRegularBackups(enableRegularBackupsCheckBox.isSelected());
            ConfigurationStore.save(appConfig, null,null);
        });
    }

    private void createUIComponents() {
        this.imagingSessionController = new ImagingSessionController();
        imagingSessionTable1 = new ImagingSessionTable(imagingSessionController, equipment, this);
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
