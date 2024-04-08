package ui.customComponents.equipmentPanelContent;

import models.equipment.*;

import javax.swing.*;

public class EquipmentPanelContent {
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

    public EquipmentPanelContent(Equipment equipment, EquipmentItem item) {
        titleBrand.setText(item.getBrand());
        titleName.setText(item.getName());
        EquipmentType type = equipment.getEquipmentType(item);
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

                label1.setText("Focal Length:");
                label2.setText("Aperture:");
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

                label1.setText("Factor");
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

    private void setAllInvisible() {
        label1.setVisible(false);
        value1.setVisible(false);
        label2.setVisible(false);
        value2.setVisible(false);
        label3.setVisible(false);
        value3.setVisible(false);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
