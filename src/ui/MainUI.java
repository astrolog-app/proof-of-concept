package ui;

import models.imagingFrames.CalibrationFrame;
import models.ImagingSession;
import models.imagingFrames.ImagingFrameList;
import models.license.Licence;
import models.settings.AppConfig;
import models.equipment.Equipment;
import models.settings.ImagingSessionConfig;
import ui.corecomponents.*;
import utils.Paths;
import utils.Images;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {
    public MainUI(Licence licence, AppConfig appConfig, List<ImagingSession> imagingSessions,
                  ImagingSessionConfig isConfig, ImagingFrameList imagingFrameList,
                  Equipment equipment) {
        Image img = Toolkit.getDefaultToolkit().getImage(Paths.IMAGE_PATH + "app_logo.png");
        setIconImage(img);

        int tabPlacement = JTabbedPane.LEFT;
        switch (appConfig.getNavigationBarPlacement()) {
            case TOP -> tabPlacement = JTabbedPane.TOP;
            case RIGHT -> tabPlacement = JTabbedPane.RIGHT;
            case BOTTOM -> tabPlacement = JTabbedPane.BOTTOM;
        }
        JTabbedPane tabbedPane = new JTabbedPane(tabPlacement);

        LogPanel logPanelClass = new LogPanel(appConfig, equipment, imagingSessions, isConfig, imagingFrameList);
        JPanel imagingHistoryPanel = logPanelClass.getPanel();

        EquipmentPanel equipmentPanelClass = new EquipmentPanel(equipment);
        JPanel equipmentPanel = equipmentPanelClass.getPanel();

        LibraryPanel libraryPanelClass = new LibraryPanel(equipment, imagingFrameList, appConfig);
        JPanel libraryPanel = libraryPanelClass.getPanel();

        SettingsPanel settingsPanelClass = new SettingsPanel(appConfig);
        JPanel settingsPanel = settingsPanelClass.getPanel();

        ImageIcon settingsIcon = Images.getThemeBasedIcon(appConfig, "settings", 14, 14);

        tabbedPane.addTab("Log", imagingHistoryPanel);
        tabbedPane.addTab("Equipment", equipmentPanel);
        tabbedPane.addTab("Library", libraryPanel);
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
    }
}
