package ui;

import models.AppConfiguration;
import ui.corecomponents.AnalysisPanel;
import ui.corecomponents.ImagingHistoryPanel;
import ui.corecomponents.SettingsPanel;

import javax.swing.*;

public class MainUI extends JFrame {
    public MainUI(AppConfiguration appConfig) {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JPanel imagingHistory = new ImagingHistoryPanel();
        JPanel analysisPanel = new AnalysisPanel();
        SettingsPanel settingsPanelClass = new SettingsPanel(appConfig);
        JPanel settingsPanel = settingsPanelClass.getPanel();

        tabbedPane.add("History", imagingHistory);
        tabbedPane.add("Analysis", analysisPanel);
        tabbedPane.add("Settings", settingsPanel);

        this.add(tabbedPane);

        setVisible(true);
        setTitle("Astro Logger V.0.01");
        if (appConfig.getStartInFullscreen()) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setSize(800, 600);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
