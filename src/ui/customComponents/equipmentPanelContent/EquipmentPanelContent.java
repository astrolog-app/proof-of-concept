package ui.customComponents.equipmentPanelContent;

import javax.swing.*;

public class EquipmentPanelContent {
    private JPanel panel1;
    private JLabel title;

    public EquipmentPanelContent(String title) {
        this.title.setText(title);
    }

    public void changeTitle(String title) {
        this.title.setText(title);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
