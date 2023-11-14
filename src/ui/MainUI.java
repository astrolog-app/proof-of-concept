package ui;

import models.settings.AppConfiguration;
import models.settings.AppTheme;
import models.equipment.Equipment;
import services.fileHandler.EquipmentStore;
import ui.corecomponents.EquipmentPanel;
import utils.Paths;
import services.fileHandler.ConfigurationStore;
import ui.corecomponents.StatisticsPanel;
import ui.corecomponents.LogPanel;
import ui.corecomponents.SettingsPanel;
import utils.Images;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    public MainUI(AppConfiguration appConfig, ConfigurationStore configStore) {
        Image img = Toolkit.getDefaultToolkit().getImage(Paths.IMAGE_PATH + "app_logo.png");
        setIconImage(img);
        Equipment equipment = new Equipment();
        EquipmentStore equipmentStore = new EquipmentStore(equipment);

        int tabPlacement = JTabbedPane.LEFT;
        switch (appConfig.getNavBarPlacement()) {
            case TOP -> tabPlacement = JTabbedPane.TOP;
            case RIGHT -> tabPlacement = JTabbedPane.RIGHT;
            case BOTTOM -> tabPlacement = JTabbedPane.BOTTOM;
        }
        JTabbedPane tabbedPane = new JTabbedPane(tabPlacement);

        LogPanel logPanelClass = new LogPanel(appConfig, configStore);
        JPanel imagingHistoryPanel = logPanelClass.getPanel();

        StatisticsPanel statisticsPanelClass = new StatisticsPanel();
        JPanel analysisPanel = statisticsPanelClass.getPanel();

        EquipmentPanel equipmentPanelClass = new EquipmentPanel(equipment, equipmentStore);
        JPanel equipmentPanel = equipmentPanelClass.getPanel();

        SettingsPanel settingsPanelClass = new SettingsPanel(appConfig, configStore);
        JPanel settingsPanel = settingsPanelClass.getPanel();

        ImageIcon settingsIcon;
        ImageIcon originalSettingsIconLight = new ImageIcon(Paths.IMAGE_PATH + "settings_light.png");
        ImageIcon scaledSettingsIconLight = Images.scaleImage(originalSettingsIconLight, 14, 14);
        ImageIcon originalSettingsIconDark = new ImageIcon(Paths.IMAGE_PATH + "settings_dark.png");
        ImageIcon scaledSettingsIconDark = Images.scaleImage(originalSettingsIconDark, 14, 14);
        if (appConfig.getTheme().equals(AppTheme.DARK)) {
            settingsIcon = scaledSettingsIconDark;
        } else {
            settingsIcon = scaledSettingsIconLight;
        }

        tabbedPane.addTab("Log", imagingHistoryPanel);
        tabbedPane.addTab("Statistics", analysisPanel);
        tabbedPane.addTab("Equipment", equipmentPanel);
        tabbedPane.addTab("", settingsIcon, settingsPanel);

        add(tabbedPane);

        setVisible(true);
        setTitle("Astro Logger");
        setMinimumSize(new Dimension(720, 550));
        if (appConfig.getStartInFullscreen()) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setSize(875, 650);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
