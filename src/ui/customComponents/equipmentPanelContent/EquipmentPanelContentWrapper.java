package ui.customComponents.equipmentPanelContent;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    public EquipmentPanelContentWrapper() {
        setLayout(new BorderLayout());
        EquipmentPanelContent e = new EquipmentPanelContent();
        add(e.getPanel());
    }
}
