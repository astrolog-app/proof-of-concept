package ui.corecomponents;

import ui.popUps.NewTelescopePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentPanel {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton newTelescopeButton;

    public EquipmentPanel() {

        newTelescopeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewTelescopePanel();
            }
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
