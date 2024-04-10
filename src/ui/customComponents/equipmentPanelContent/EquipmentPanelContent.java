package ui.customComponents.equipmentPanelContent;

import models.equipment.*;
import models.settings.AppConfig;
import ui.corecomponents.EquipmentPanel;
import ui.popUps.rowEditors.EquipmentRowEditor;
import utils.Images;

import javax.swing.*;

public class EquipmentPanelContent {
    private final Equipment equipment;
    private EquipmentItem item;
    private final EquipmentPanel equipmentPanel;
    private EquipmentType type;
    private JPanel panel1;
    private JLabel value1;
    private JLabel value2;
    private JLabel label2;
    private JLabel label1;
    private JLabel label3;
    private JLabel value3;
    private JLabel brand;
    private JLabel name;
    private JLabel used;
    private JLabel titleBrand;
    private JLabel titleName;
    private JButton editButton;

    public EquipmentPanelContent(Equipment equipment, EquipmentItem item, AppConfig appConfig,
                                 EquipmentPanel equipmentPanel) {
        this.equipment = equipment;
        this.item = item;
        this.equipmentPanel = equipmentPanel;
        type = equipment.getEquipmentType(item);

        ImageIcon binIcon = Images.getThemeBasedIcon(appConfig, "edit", 30, 30);
        editButton.setIcon(binIcon);
        editButton.setText("");

        update();
        handleActions();
    }

    private void update() {
        titleBrand.setText(item.getBrand());
        titleName.setText(item.getName());
        brand.setText(item.getBrand());
        name.setText(item.getName());
        if (item.getUsed()) {
            used.setText("Yes");
        } else {
            used.setText("No");
        }

        setAllInvisible();

        switch (type) {
            case TELESCOPE -> {
                label1.setVisible(true);
                value1.setVisible(true);
                label2.setVisible(true);
                value2.setVisible(true);

                label1.setText("Focal Length [mm]:");
                label2.setText("Aperture [mm]:");
                value1.setText(Integer.toString(((Telescope) item).getFocalLength()));
                value2.setText(Integer.toString(((Telescope) item).getAperture()));
            }
            case FILTER -> {
                label1.setVisible(true);
                value1.setVisible(true);

                label1.setText("Filter Type:");
                value1.setText(((Filter) item).getFilterType());
            }
            case FLATTENER -> {
                label1.setVisible(true);
                value1.setVisible(true);

                label1.setText("Magnification:");
                value1.setText(((Flattener) item).getFactor() + "x");
            }
            case CAMERA -> {
                label1.setVisible(true);
                value1.setVisible(true);
                label2.setVisible(true);
                value2.setVisible(true);
                label3.setVisible(true);
                value3.setVisible(true);

                label1.setText("Chip Size:");
                label2.setText("Megapixel:");
                label3.setText("Type:");
                value1.setText(((Camera) item).getChipSize());
                value2.setText(Double.toString(((Camera) item).getMegaPixel()));
                if (((Camera) item).getRgb()) {
                    value3.setText("Color");
                } else {
                    value3.setText("Monochrome");
                }
            }
            case MOUNT, ACCESSOIRE -> {}
        }
    }

    private void handleActions() {
        editButton.addActionListener(e -> new EquipmentRowEditor(type, equipment, item, equipmentPanel));
    }

    private void setAllInvisible() {
        label1.setVisible(false);
        value1.setVisible(false);
        label2.setVisible(false);
        value2.setVisible(false);
        label3.setVisible(false);
        value3.setVisible(false);
    }

    public void setItem(EquipmentItem item) {
        if (item != null) {
            this.item = item;
            type = equipment.getEquipmentType(item);
            update();
        }
    }

    public JPanel getPanel() {
        return panel1;
    }
}
