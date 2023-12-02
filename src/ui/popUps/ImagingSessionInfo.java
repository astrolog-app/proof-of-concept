package ui.popUps;

import javax.swing.*;

public class ImagingSessionInfo extends JDialog {
    private JPanel mainPanel;

    public ImagingSessionInfo() {
        add(mainPanel);
        setTitle("Test");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
