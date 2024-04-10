package ui.customComponents.equipmentPanelContent;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.settings.AppConfig;
import ui.corecomponents.EquipmentPanel;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    private final Equipment equipment;
    private EquipmentItem item;
    private final AppConfig appConfig;
    private final EquipmentPanel equipmentPanel;
    private EquipmentPanelContent e;
    private boolean showNull = true;

    public EquipmentPanelContentWrapper(Equipment equipment, EquipmentItem item, AppConfig appConfig,
                                        EquipmentPanel equipmentPanel) {
        this.equipment = equipment;
        this.item = item;
        this.appConfig = appConfig;
        this.equipmentPanel = equipmentPanel;

        setLayout(new BorderLayout());

        if (item == null) {
            add(new JLabel("Select Equipment Item"));
        } else {
            e = new EquipmentPanelContent(equipment, this.item, appConfig, equipmentPanel);
            add(e.getPanel());
            showNull = false;
        }
    }

    public void setItem(EquipmentItem item) {
        this.item = item;

        if (showNull) {
            removeAll();
            e = new EquipmentPanelContent(equipment, this.item, appConfig, equipmentPanel);
            add(e.getPanel());
            revalidate();
            repaint();
            showNull = false;
        } else {
            e.setItem(item);
        }
    }
}
