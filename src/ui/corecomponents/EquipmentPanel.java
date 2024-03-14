package ui.corecomponents;

import models.EquipmentListModel;
import models.equipment.Equipment;
import ui.customComponents.equipmentPanelContent.EquipmentPanelContentWrapper;

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

        list1.setModel(new EquipmentListModel(equipment));
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        content = new EquipmentPanelContentWrapper();
    }
}
