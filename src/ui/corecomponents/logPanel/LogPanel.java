package ui.corecomponents.logPanel;

import models.settings.AppConfiguration;
import services.fileHandler.ConfigurationStore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogPanel {
    AppConfiguration appConfig;
    ConfigurationStore configStore;
    private JPanel panel1;
    private JLabel placeHolder1;
    private JButton fromExistingFolderButton;
    private JButton manuallyButton;
    private JButton createLocalBackupButton;
    private JCheckBox enableRegularBackupsCheckBox;
    private JButton configureRegularBackupsButton;
    private JButton saveBackupConfigButton;

    public LogPanel(AppConfiguration appConfig, ConfigurationStore configStore) {
        this.appConfig = appConfig;
        this.configStore = configStore;

        backupHandler();
    }

    private void backupHandler() {
        enableRegularBackupsCheckBox.setSelected(appConfig.getEnableRegularBackups());

        enableRegularBackupsCheckBox.addActionListener(e -> {
            appConfig.setEnableRegularBackups(enableRegularBackupsCheckBox.isSelected());
            configStore.save(null);
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}
