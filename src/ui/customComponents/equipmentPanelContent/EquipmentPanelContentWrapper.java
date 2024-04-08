package ui.customComponents.equipmentPanelContent;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    private final Equipment equipment;
    private EquipmentItem item;

    public EquipmentPanelContentWrapper(Equipment equipment, EquipmentItem item) {
        this.equipment = equipment;
        this.item = item;

        setLayout(new BorderLayout());
        if (this.item == null) {
            add(new JLabel("Null"));
        } else {
            EquipmentPanelContent e = new EquipmentPanelContent(equipment, this.item);
            add(e.getPanel());
        }
    }

    public void setItem(EquipmentItem item) {
        removeAll();
        this.item = item;
        EquipmentPanelContent eq = new EquipmentPanelContent(equipment, this.item);
        add(eq.getPanel());
        revalidate();
        repaint();
    }
}
