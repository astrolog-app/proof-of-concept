package ui.corecomponents;

import models.EquipmentListModel;
import models.equipment.Equipment;
import models.settings.AppConfig;
import ui.customComponents.equipmentPanelContent.EquipmentPanelContentWrapper;
import ui.popUps.EquipmentTypeSelector;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class EquipmentPanel {
    private final Equipment equipment;
    private final AppConfig appConfig;
    private final EquipmentListModel elm;
    private String selectedEquipment;
    private JPanel mainPanel;
    private JLabel placeHolder1;
    private JList<String> list1;
    private JPanel content;
    private JButton addButton;
    private JComboBox listStateBox;
    private JButton editButton;

    public EquipmentPanel(Equipment equipment, AppConfig appConfig) {
        this.equipment = equipment;
        this.appConfig = appConfig;
        elm = new EquipmentListModel(equipment, EquipmentListModel.State.USED);

        list1.setModel(elm);

        handleActions();
    }

    private void handleActions() {
        addButton.addActionListener(e -> new EquipmentTypeSelector(equipment, elm));

        list1.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                ((EquipmentPanelContentWrapper) content).setItem(equipment.getItemFromViewName(list1.getSelectedValue()));
                selectedEquipment = list1.getSelectedValue();
            }
        });

        listStateBox.addItemListener(e -> {
            EquipmentListModel.State state = EquipmentListModel.State.ALL;
            if (listStateBox.getSelectedIndex() == 0) {
                state = EquipmentListModel.State.USED;
            }
            if (listStateBox.getSelectedIndex() == 1) {
                state = EquipmentListModel.State.UNUSED;
            }

            elm.changeState(state);
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        content = new EquipmentPanelContentWrapper(equipment, null, appConfig);
    }
}
