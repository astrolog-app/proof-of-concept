package ui.corecomponents;

import models.equipment.Equipment;
import models.equipment.EquipmentType;
import ui.customComponents.tables.equipmentTable.EquipmentTableWrapper;

import javax.swing.*;

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
        abc = new EquipmentTableWrapper(equipment, EquipmentType.CAMERA);
    }
}
