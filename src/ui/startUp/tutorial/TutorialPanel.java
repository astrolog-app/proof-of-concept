package ui.startUp.tutorial;

import javax.swing.*;

public class TutorialPanel extends JDialog {
    private JPanel panel1;
    private JButton nextButton;
    private JButton previousButton;

    public TutorialPanel() {
        add(panel1);

        setTitle("Welcome!");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
