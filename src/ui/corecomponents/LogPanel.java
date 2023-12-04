package ui.corecomponents;

import controllers.ImagingSessionController;
import models.equipment.Equipment;
import models.settings.AppConfiguration;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.EquipmentStore;
import ui.customComponents.LogTableScrollPane;
import ui.popUps.NewImagingSessionManually;
import utils.Images;
import utils.Paths;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogPanel {
    ImagingSessionController imagingSessionController = new ImagingSessionController();

    AppConfiguration appConfig;
    private JPanel panel1;
    private JLabel placeHolder1;
    private JButton fromExistingFolderButton;
    private JButton manuallyButton;
    private JButton createLocalBackupButton;
    private JCheckBox enableRegularBackupsCheckBox;
    private JButton configureRegularBackupsButton;
    private LogTableScrollPane logTableScrollPane1;
    private JTextField textField1;
    private JButton deleteButton;
    private JButton detailsButton;
    private JButton xMarklButton;
    private JButton saveBackupConfigButton;

    public LogPanel(AppConfiguration appConfig, Equipment equipment) {
        this.appConfig = appConfig;

        ImageIcon binIcon = Images.getThemeBasedIcon(appConfig, "bin", 18, 18);
        deleteButton.setIcon(binIcon);
        deleteButton.setText("");

        ImageIcon xMarkIcon = Images.getThemeBasedIcon(appConfig, "x-mark", 18, 18);
        xMarklButton.setIcon(xMarkIcon);
        xMarklButton.setText("");

        backupHandler();

        manuallyButton.addActionListener(e -> imagingSessionController.addImagingSessionManually(equipment));
        detailsButton.addActionListener(e -> imagingSessionController.showImagingSessionDetails());
        deleteButton.addActionListener(e -> imagingSessionController.removeImagingSession());
    }

    private void backupHandler() {
        enableRegularBackupsCheckBox.setSelected(appConfig.getEnableRegularBackups());

        enableRegularBackupsCheckBox.addActionListener(e -> {
            appConfig.setEnableRegularBackups(enableRegularBackupsCheckBox.isSelected());
            ConfigurationStore.save(appConfig, null);
        });
    }

    private void createUIComponents() {
        logTableScrollPane1 = new LogTableScrollPane(imagingSessionController);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
