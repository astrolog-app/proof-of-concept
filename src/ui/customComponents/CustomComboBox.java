package ui.customComponents;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomComboBox extends JComboBox<String> {
    public CustomComboBox(List<String> content, Class<? extends JDialog> newPanelClass) {
        addItem(null);
        addItem("Add New...");
        for (String s : content) {
            addItem(s);
        }

        setFontsAndColorsForItems(this);

        addActionListener(e -> removeItem(null));

        addActionListener(e -> {
            if (getSelectedItem() != null && getSelectedItem().equals("Add New...")) {
                try {
                    newPanelClass.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private static void setFontsAndColorsForItems(JComboBox<String> comboBox) {
        Map<String, Map<String, Object>> itemStyles = new HashMap<>();

        Map<String, Object> styleItem2 = new HashMap<>();
        styleItem2.put("Font", new Font(null, Font.BOLD, 14));
        styleItem2.put("Color", null);
        itemStyles.put("Add New...", styleItem2);

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
}
