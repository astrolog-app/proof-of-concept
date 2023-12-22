package ui.popUps;

import models.imagingSessions.ImagingSession;

import javax.swing.*;

public class ImagingSessionInfo extends JDialog {
    private JPanel mainPanel;
    private JLabel test;

    public ImagingSessionInfo(ImagingSession session) {
        test.setText(session.getLightFrame().getDate());

        add(mainPanel);
        setTitle("Test");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
