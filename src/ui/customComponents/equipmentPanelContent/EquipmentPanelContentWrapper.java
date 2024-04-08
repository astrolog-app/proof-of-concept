package ui.customComponents.equipmentPanelContent;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    private final Equipment equipment;
    private EquipmentPanelContent e;

    public EquipmentPanelContentWrapper(Equipment equipment, EquipmentItem item) {
        this.equipment = equipment;

        setLayout(new BorderLayout());
        if (item == null) {
            add(new JLabel("Null"));
        } else {
            e = new EquipmentPanelContent(equipment, item);
            add(e.getPanel());
        }
    }

    public void setItem(EquipmentItem item) {
        EquipmentPanelContent eq = new EquipmentPanelContent(equipment, item);
        add(eq.getPanel());
        revalidate();
    }
}
