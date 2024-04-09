package ui.customComponents.equipmentPanelContent;

import models.EquipmentListModel;
import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.settings.AppConfig;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    private final Equipment equipment;
    private EquipmentItem item;
    private final AppConfig appConfig;
    private final EquipmentListModel listModel;

    public EquipmentPanelContentWrapper(Equipment equipment, EquipmentItem item, AppConfig appConfig,
                                        EquipmentListModel listModel) {
        this.equipment = equipment;
        this.item = item;
        this.appConfig = appConfig;
        this.listModel = listModel;

        setLayout(new BorderLayout());
        if (this.item == null) {
            add(new JLabel("Null"));
        } else {
            EquipmentPanelContent e = new EquipmentPanelContent(equipment, this.item, appConfig, listModel, this);
            add(e.getPanel());
        }
    }

    public void setItem(EquipmentItem item) {
        removeAll();
        this.item = item;
        EquipmentPanelContent eq = new EquipmentPanelContent(equipment, this.item, appConfig, listModel, this);
        add(eq.getPanel());
        revalidate();
        repaint();
    }
}
