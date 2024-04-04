package ui.customComponents.equipmentPanelContent;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanelContentWrapper extends JPanel {
    private final EquipmentPanelContent e;

    public EquipmentPanelContentWrapper(String title) {
        setLayout(new BorderLayout());
        e = new EquipmentPanelContent(title);
        add(e.getPanel());
    }

    public void changeTitle(String title) {
        e.changeTitle(title);
    }
}
