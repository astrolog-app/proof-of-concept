package ui.corecomponents;

import models.equipment.Equipment;
import models.settings.AppConfiguration;
import services.fileHandler.ConfigurationStore;
import services.fileHandler.EquipmentStore;
import ui.customComponents.LogTableScrollPane;
import ui.popUps.NewImagingSessionManually;

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
    private LogTableScrollPane logTableScrollPane1;
    private JButton saveBackupConfigButton;

    public LogPanel(AppConfiguration appConfig, ConfigurationStore configStore, Equipment equipment, EquipmentStore equipmentStore) {
        this.appConfig = appConfig;
        this.configStore = configStore;

        backupHandler();
        manuallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewImagingSessionManually p = new NewImagingSessionManually(equipment, equipmentStore);
            }
        });
    }

    private void backupHandler() {
        enableRegularBackupsCheckBox.setSelected(appConfig.getEnableRegularBackups());

        enableRegularBackupsCheckBox.addActionListener(e -> {
            appConfig.setEnableRegularBackups(enableRegularBackupsCheckBox.isSelected());
            configStore.save(null);
        });
    }

    private void createUIComponents() {
        logTableScrollPane1 = new LogTableScrollPane();
    }

    public JPanel getPanel() {
        return panel1;
    }
}
