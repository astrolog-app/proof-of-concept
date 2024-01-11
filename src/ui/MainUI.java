package ui;

import models.license.Licence;
import models.settings.AppConfig;
import models.equipment.Equipment;
import services.fileHandler.EquipmentStore;
import ui.corecomponents.*;
import ui.popUps.LicenceRequest;
import utils.Enums;
import utils.Paths;
import utils.Images;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    private Licence licence;

    public MainUI(Licence licence, AppConfig appConfig) {
        this.licence = licence;

        Equipment equipment = EquipmentStore.load();

        Image img = Toolkit.getDefaultToolkit().getImage(Paths.IMAGE_PATH + "app_logo.png");
        setIconImage(img);

        int tabPlacement = JTabbedPane.LEFT;
        switch (appConfig.getNavigationBarPlacement()) {
            case TOP -> tabPlacement = JTabbedPane.TOP;
            case RIGHT -> tabPlacement = JTabbedPane.RIGHT;
            case BOTTOM -> tabPlacement = JTabbedPane.BOTTOM;
        }
        JTabbedPane tabbedPane = new JTabbedPane(tabPlacement);

        LogPanel logPanelClass = new LogPanel(appConfig, equipment);
        JPanel imagingHistoryPanel = logPanelClass.getPanel();

        StatisticsPanel statisticsPanelClass = new StatisticsPanel();
        JPanel analysisPanel = statisticsPanelClass.getPanel();

        EquipmentPanel equipmentPanelClass = new EquipmentPanel(equipment);
        JPanel equipmentPanel = equipmentPanelClass.getPanel();

        SettingsPanel settingsPanelClass = new SettingsPanel(appConfig);
        JPanel settingsPanel = settingsPanelClass.getPanel();

        ImageIcon settingsIcon = Images.getThemeBasedIcon(appConfig, "settings", 14, 14);

        tabbedPane.addTab("Log", imagingHistoryPanel);
        tabbedPane.addTab("Statistics", analysisPanel);
        tabbedPane.addTab("Equipment", equipmentPanel);
        tabbedPane.addTab("", settingsIcon, settingsPanel);

        add(tabbedPane);

        setVisible(true);
        setTitle("AstroLog");
        setMinimumSize(new Dimension(650, 500));
        if (appConfig.getStartInFullscreen()) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setSize(1000, 800);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        test();
    }

    private void test() {
        if (licence == null || licence.getLicenceKey() == null) {
            new LicenceRequest(this);
        } else {
            setTitle("AstroLog " + Enums.enumToString(licence.getLicenceType()));
        }
    }
}
