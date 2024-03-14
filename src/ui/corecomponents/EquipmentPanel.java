package ui.corecomponents;

import models.equipment.Equipment;

import javax.swing.*;

public class EquipmentPanel {
    private final Equipment equipment;
    private JPanel mainPanel;
    private JLabel placeHolder1;
    private JTabbedPane tabbedPane1;
    private JList<String> list1;
    private JList<String> list2;
    private JList<String> list3;
    private JPanel content;

    public EquipmentPanel(Equipment equipment) {
        this.equipment = equipment;
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        content = new JPanel();
    }
}
