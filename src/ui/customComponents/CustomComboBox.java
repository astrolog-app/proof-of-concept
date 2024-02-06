package ui.customComponents;

import models.equipment.*;
import ui.popUps.EquipmentItemPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomComboBox extends JComboBox<String> {
    private final EquipmentType equipmentType;
    private final Equipment equipment;
    private final String newItemString = "Add New...";
    private final List<String> content = new ArrayList<>();
    private boolean nullItem = true;

    public CustomComboBox(EquipmentType equipmentType, Equipment equipment) {
        this.equipmentType = equipmentType;
        this.equipment = equipment;

        //updateData();
        setFontsAndColorsForItems(this);

        addActionListener(e -> {
            if (getSelectedItem() != null && !getSelectedItem().equals(newItemString)) {
                removeItem(null);
                nullItem = false;
            }
        });

        addActionListener(e -> {
            if (getSelectedItem() != null && getSelectedItem().equals(newItemString)) {
                List<String> oldList = new ArrayList<>(content);

                setSelectedIndex(0); // TODO: set the actual ListItem that was selected before
                new EquipmentItemPanel(equipmentType, equipment, null);
                //updateData();

                if (oldList.size() != content.size()) {
                    removeItem(null);
                    setSelectedItem(findNewItem(oldList));
                }
            }
        });
    }

    private void updateData() {
        removeAllItems();
        content.clear();

        if (nullItem) {
            addItem(null);
        }
        addItem(newItemString);

        switch (equipmentType) {
            case TELESCOPE -> {
                for (Telescope t : equipment.getTelescopes().values()) {
                    content.add(t.getName());
                }
            }
            case MOUNT -> {
                for (Mount m : equipment.getMounts().values()) {
                    content.add(m.getName());
                }
            }
            case CAMERA -> {
                for (Camera c : equipment.getCameras().values()) {
                    content.add(c.getName());
                }
            }
            case FILTER -> {
                for (Filter f : equipment.getFilters().values()) {
                    content.add(f.getName());
                }
            }
            case FLATTENER -> {
                for (Flattener f : equipment.getFlatteners().values()) {
                    content.add(f.getName());
                }
            }
            case ACCESSOIRE -> {
                for (Accessoire a : equipment.getAccessoires().values()) {
                    content.add(a.getName());
                }
            }
        }

        for (String s : content) {
            addItem(s);
        }
    }

    private void setFontsAndColorsForItems(JComboBox<String> comboBox) {
        Map<String, Map<String, Object>> itemStyles = new HashMap<>();

        Map<String, Object> styleItem2 = new HashMap<>();
        styleItem2.put("Font", new Font(null, Font.BOLD, 14));
        styleItem2.put("Color", null);
        itemStyles.put(newItemString, styleItem2);

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Set the font and color based on the item
                if (value != null && itemStyles.containsKey(value.toString())) {
                    Map<String, Object> style = itemStyles.get(value.toString());

                    // Set the font
                    if (style.containsKey("Font")) {
                        component.setFont((Font) style.get("Font"));
                    }

                    // Set the color
                    if (style.containsKey("Color")) {
                        component.setForeground((Color) style.get("Color"));
                    }
                }

                return component;
            }
        });
    }

    private String findNewItem(List<String> oldList) {
        for (String s : content) {
            if (!oldList.contains(s)) {
                return s;
            }
        }

        return null;
    }
}
