package ui;

import models.AppConfiguration;
import models.AppTheme;
import models.Paths;
import services.fileHandler.ConfigurationStore;
import ui.corecomponents.AnalysisPanel;
import ui.corecomponents.ImagingHistoryPanel;
import ui.corecomponents.SettingsPanel;
import utils.Images;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    public MainUI(AppConfiguration appConfig, ConfigurationStore configStore) {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        ImagingHistoryPanel imagingHistoryPanelClass = new ImagingHistoryPanel();
        JPanel imagingHistoryPanel = imagingHistoryPanelClass.getPanel();

        AnalysisPanel analysisPanelClass = new AnalysisPanel();
        JPanel analysisPanel = analysisPanelClass.getPanel();

        SettingsPanel settingsPanelClass = new SettingsPanel(appConfig, configStore);
        JPanel settingsPanel = settingsPanelClass.getPanel();

        ImageIcon settingsIcon;
        ImageIcon originalSettingsIconLight = new ImageIcon(Paths.IMAGE_PATH + "/settingsLIGHT.png");
        ImageIcon scaledSettingsIconLight = Images.scaleImage(originalSettingsIconLight, 16, 16);
        ImageIcon originalSettingsIconDark = new ImageIcon(Paths.IMAGE_PATH + "/settingsDARK.png");
        ImageIcon scaledSettingsIconDark = Images.scaleImage(originalSettingsIconDark, 16, 16);
        if (appConfig.getTheme().equals(AppTheme.DARK)) {
            settingsIcon = scaledSettingsIconDark;
        } else {
            settingsIcon = scaledSettingsIconLight;
        }

        tabbedPane.addTab("History", imagingHistoryPanel);
        tabbedPane.addTab("Analysis", analysisPanel);
        tabbedPane.addTab("", settingsIcon, settingsPanel);

        this.add(tabbedPane);

        setVisible(true);
        setTitle("Astro Logger");
        setMinimumSize(new Dimension(720, 550));
        if (appConfig.getStartInFullscreen()) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setSize(800, 600);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
