package ui.startUp;

import utils.Paths;

import javax.swing.*;
import java.awt.*;

public class StartUpPanel extends JDialog {
    private JPanel mainPanel;
    private JPanel backgroundPanel;
    private JProgressBar progressBar1;
    private JLabel progressLabel;

    public StartUpPanel() {
        setProgressLabel("starting application");
        setUndecorated(true);
        setSize(650, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        add(mainPanel);
        progressBar1.setValue(0);
        setAlwaysOnTop(true);
        setVisible(true);

        progressBar1.setMinimum(0);
        progressBar1.setMaximum(6);
    }

    private void createUIComponents() {
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundImage = new ImageIcon(Paths.IMAGE_PATH + "start_up_background.png");
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, -55, getWidth(), 410, null); // TODO: correct image ratio
            }
        };
    }

    public void setProgressLabel(String text) {
        progressLabel.setText(text + "...");
    }

    public void increaseProgress() {
        progressBar1.setValue(progressBar1.getValue() + 1);
    }
}
