package ui.corecomponents;

import models.equipment.Equipment;
import services.fileHandler.EquipmentStore;
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
    private JLabel placeHolder1;

    public EquipmentPanel(Equipment equipment) {
        newTelescopeButton.addActionListener(e -> new NewTelescopePanel(equipment));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
