package ui.startUp;

import javax.swing.*;

public class StartUpPanel extends JDialog {
    private JPanel mainPanel;
    private JProgressBar progressBar1;
    public StartUpPanel() {
        setUndecorated(true);
        setVisible(true);
        setSize(650, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        add(mainPanel);
    }
}
