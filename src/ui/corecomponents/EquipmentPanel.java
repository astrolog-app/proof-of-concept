package ui.corecomponents;

import models.EquipmentListModel;
import models.equipment.Equipment;
import ui.customComponents.equipmentPanelContent.EquipmentPanelContentWrapper;
import ui.popUps.EquipmentTypeSelector;

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
    private JButton addButton;
    private JButton editButton;

    public EquipmentPanel(Equipment equipment) {
        this.equipment = equipment;

        EquipmentListModel elm = new EquipmentListModel(equipment, EquipmentListModel.State.USED);

        list1.setModel(elm);
        list2.setModel(elm);
        list3.setModel(elm);


        list1.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                ((EquipmentPanelContentWrapper) content).changeTitle(list1.getSelectedValue());
            }
        });
        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> new EquipmentTypeSelector(equipment));
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        content = new EquipmentPanelContentWrapper("test");
    }
}
