package ui.popUps;

import models.equipment.Equipment;
import models.equipment.Telescope;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class NewTelescopePanel extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JComboBox brandField;
    private JTextField focalLengthField;
    private JTextField apertureField;

    public NewTelescopePanel(Equipment equipment) {
        List<String> brandList = new ArrayList<>(equipment.getAllBrands());
        Collections.sort(brandList);

        setFontsAndColorsForItems(brandField);

        brandField.addItem(null);
        //brandField.addItem("Select Brand");
        brandField.addItem("Add New Brand");
        for (String brand : brandList) {
            brandField.addItem(brand);
        }

        saveButton.setEnabled(false);

        saveButton.addActionListener(e -> {
            int focalLength = Integer.parseInt(focalLengthField.getText());
            int aperture = Integer.parseInt(apertureField.getText());
            Telescope telescope = new Telescope(UUID.randomUUID(), true, nameField.getText(), "test", focalLength, aperture);
            equipment.addTelescope(telescope);
            //EquipmentStore.save(equipment, null);
            dispose();
        });

        brandField.addActionListener(e -> brandField.removeItem(null));

        cancelButton.addActionListener(e -> this.dispose());

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add New Telescope");
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static void setFontsAndColorsForItems(JComboBox<String> comboBox) {
        // Create a map to store fonts and colors for each item
        Map<String, Map<String, Object>> itemStyles = new HashMap<>();

        // Set custom fonts and colors for specific items
       // Map<String, Object> styleItem1 = new HashMap<>();
       // styleItem1.put("Font", new Font(null, Font.PLAIN, 12));
       // styleItem1.put("Color", null);
       // itemStyles.put("Select Brand", styleItem1);

        Map<String, Object> styleItem2 = new HashMap<>();
        styleItem2.put("Font", new Font(null, Font.BOLD, 14));
        styleItem2.put("Color", null);
        itemStyles.put("Add New Brand", styleItem2);

        // Set the custom renderer for the JComboBox
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
