package ui;

import models.AppConfiguration;
import ui.corecomponents.AnalysisPanel;
import ui.corecomponents.ImagingHistoryPanel;
import ui.corecomponents.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    public MainUI(AppConfiguration appConfig) {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        ImagingHistoryPanel imagingHistoryPanelClass = new ImagingHistoryPanel();
        JPanel imagingHistoryPanel = imagingHistoryPanelClass.getPanel();

        AnalysisPanel analysisPanelClass = new AnalysisPanel();
        JPanel analysisPanel = analysisPanelClass.getPanel();

        SettingsPanel settingsPanelClass = new SettingsPanel(appConfig);
        JPanel settingsPanel = settingsPanelClass.getPanel();

        tabbedPane.add("History", imagingHistoryPanel);
        tabbedPane.add("Analysis", analysisPanel);
        tabbedPane.add("Settings", settingsPanel);

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
