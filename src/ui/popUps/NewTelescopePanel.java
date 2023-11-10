package ui.popUps;

import javax.swing.*;
import java.awt.*;

public class NewTelescopePanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JTextField brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public NewTelescopePanel() {
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(200,125));
        setSize(500, 275);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
