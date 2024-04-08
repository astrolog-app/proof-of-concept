package ui.corecomponents;

import models.EquipmentListModel;
import models.equipment.Equipment;
import ui.customComponents.equipmentPanelContent.EquipmentPanelContentWrapper;

import javax.swing.*;

public class EquipmentPanel {
    private final Equipment equipment;
    private String selectedEquipment;
    private JPanel mainPanel;
    private JLabel placeHolder1;
    private JList<String> list1;
    private JPanel content;
    private JButton addButton;
    private JButton editButton;

    public EquipmentPanel(Equipment equipment) {
        this.equipment = equipment;

        EquipmentListModel elm = new EquipmentListModel(equipment, EquipmentListModel.State.USED);

        list1.setModel(elm);

        handleActions();
    }

    private void handleActions() {
        //addButton.addActionListener(e -> new EquipmentTypeSelector(equipment));
        //editButton.addActionListener(e -> new EquipmentRowEditor(
        //        equipment.getEquipmentTypeFromViewName(selectedEquipment),
        //        equipment,
                        //        equipment.getItemFromViewName(selectedEquipment))
        //);

        list1.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                ((EquipmentPanelContentWrapper) content).setItem(equipment.getItemFromViewName(list1.getSelectedValue()));
                selectedEquipment = list1.getSelectedValue();
            }
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        content = new EquipmentPanelContentWrapper(equipment, null);
    }
}
