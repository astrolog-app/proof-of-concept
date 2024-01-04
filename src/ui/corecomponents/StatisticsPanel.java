package ui.corecomponents;

import ui.customComponents.StatisticsGraph;

import javax.swing.*;

public class StatisticsPanel {
    private JPanel mainPanel;
    private JLabel placeHolder1;
    private StatisticsGraph statisticsGraph;

    private void createUIComponents() {
        statisticsGraph = new StatisticsGraph();
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
