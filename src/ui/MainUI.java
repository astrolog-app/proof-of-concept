package ui;

import models.AppConfiguration;
import models.Paths;
import services.fileHandler.ConfigurationStore;
import ui.corecomponents.AnalysisPanel;
import ui.corecomponents.ImagingHistoryPanel;
import ui.corecomponents.SettingsPanel;

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

        ImageIcon originalSettingsIcon = new ImageIcon(Paths.IMAGE_PATH + "/settings.png");
        int width = 16; // Desired width of the icon
        int height = 16; // Desired height of the icon
        Image scaledImage = originalSettingsIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledSettingsIcon = new ImageIcon(scaledImage);

        tabbedPane.addTab("History", imagingHistoryPanel);
        tabbedPane.addTab("Analysis", analysisPanel);
        tabbedPane.addTab("", scaledSettingsIcon, settingsPanel);

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
