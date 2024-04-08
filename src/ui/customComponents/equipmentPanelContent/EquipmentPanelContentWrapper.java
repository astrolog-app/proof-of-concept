package ui.customComponents.equipmentPanelContent;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.settings.AppConfig;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    private final Equipment equipment;
    private EquipmentItem item;
    private final AppConfig appConfig;

    public EquipmentPanelContentWrapper(Equipment equipment, EquipmentItem item, AppConfig appConfig) {
        this.equipment = equipment;
        this.item = item;
        this.appConfig = appConfig;

        setLayout(new BorderLayout());
        if (this.item == null) {
            add(new JLabel("Null"));
        } else {
            EquipmentPanelContent e = new EquipmentPanelContent(equipment, this.item, appConfig);
            add(e.getPanel());
        }
    }

    public void setItem(EquipmentItem item) {
        removeAll();
        this.item = item;
        EquipmentPanelContent eq = new EquipmentPanelContent(equipment, this.item, appConfig);
        add(eq.getPanel());
        revalidate();
        repaint();
    }
}
