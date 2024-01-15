package ui.corecomponents;

import models.equipment.Equipment;
import ui.customComponents.equipmentTable.EquipmentTable;
import ui.customComponents.equipmentTable.EquipmentTableWrapper;

import javax.swing.*;
import java.util.ArrayList;

public class EquipmentPanel {
    private final Equipment equipment;
    private JPanel mainPanel;
    private JLabel placeHolder1;
    private EquipmentTableWrapper abc;

    public EquipmentPanel(Equipment equipment) {
        this.equipment = equipment;
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        abc = new EquipmentTableWrapper(equipment);
    }
}
