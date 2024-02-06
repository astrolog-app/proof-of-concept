package ui.startUp;

import utils.Paths;

import javax.swing.*;
import java.awt.*;

public class StartUpPanel extends JDialog {
    private JPanel mainPanel;
    private JPanel backgroundPanel;
    private JProgressBar progressBar1;

    public StartUpPanel() {
        setUndecorated(true);
        setVisible(true);
        setSize(650, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        add(mainPanel);
        progressBar1.setValue(20);
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
}
