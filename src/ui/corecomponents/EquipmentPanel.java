package ui.corecomponents;

import models.equipment.Equipment;
import models.equipment.EquipmentType;
import ui.popUps.NewEquipmentItemPanel;

import javax.swing.*;

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
        newTelescopeButton.addActionListener(e -> new NewEquipmentItemPanel(EquipmentType.TELESCOPE, equipment));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
