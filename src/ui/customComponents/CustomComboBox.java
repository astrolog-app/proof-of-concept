package ui.customComponents;

import models.calibrationLibrary.CalibrationLibrary;
import models.calibrationLibrary.CalibrationType;
import models.equipment.*;
import models.settings.AppConfig;
import ui.popUps.rowEditors.EquipmentRowEditor;
import ui.popUps.rowEditors.LibraryRowEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

public class CustomComboBox extends JComboBox<String> {
    private enum BoxType {
        EQUIPMENT,
        CALIBRATION
    }
    private final EquipmentType equipmentType;
    private final CalibrationType calibrationType;
    private final Equipment equipment;
    private final BoxType boxType;
    private final AppConfig appConfig;
    private final List<CalibrationLibrary> calibrationLibrary;
    private final String newItemString = "Add New...";
    private final List<String> content = new ArrayList<>();
    private boolean nullItem = true;
    private int selectedIndex = 0;

    public CustomComboBox(EquipmentType equipmentType, Equipment equipment) {
        this.equipmentType = equipmentType;
        this.calibrationType = null;
        this.equipment = equipment;
        this.boxType = BoxType.EQUIPMENT;
        this.appConfig = null;
        this.calibrationLibrary = null;

        init();
    }

    public CustomComboBox(CalibrationType calibrationType, Equipment equipment, AppConfig appConfig, List<CalibrationLibrary> calibrationLibrary) {
        this.equipmentType = null;
        this.calibrationType = calibrationType;
        this.equipment = equipment;
        this.boxType = BoxType.CALIBRATION;
        this.appConfig = appConfig;
        this.calibrationLibrary = calibrationLibrary;

        init();
    }

    private void init() {
        updateData();
        setFontsAndColorsForItems(this);
        handleActions();
    }

    private void updateData() {
        removeAllItems();
        content.clear();

        if (nullItem) {
            addItem(null);
        }
        addItem(newItemString);

        switch (boxType) {
            case EQUIPMENT -> {
                switch (equipmentType) {
                    case TELESCOPE -> equipment.getTelescopes().values().forEach(t -> content.add(t.getViewName()));
                    case MOUNT -> equipment.getMounts().values().forEach(m -> content.add(m.getViewName()));
                    case CAMERA -> equipment.getCameras().values().forEach(c -> content.add(c.getViewName()));
                    case FILTER -> equipment.getFilters().values().forEach(f -> content.add(f.getViewName()));
                    case FLATTENER -> equipment.getFlatteners().values().forEach(f -> content.add(f.getViewName()));
                    case ACCESSOIRE -> equipment.getAccessoires().values().forEach(a -> content.add(a.getViewName()));
                }
            }
            case CALIBRATION -> {
                switch (calibrationType) {
                    case BIAS -> {
                        for (CalibrationLibrary lib : calibrationLibrary) {
                            if (lib.getCalibrationType() == CalibrationType.BIAS) {
                                content.add(lib.getCamera(equipment).getViewName());
                            }
                        }
                    }
                    case DARK -> {
                        for (CalibrationLibrary lib : calibrationLibrary) {
                            if (lib.getCalibrationType() == CalibrationType.DARK) {
                                content.add(lib.getCamera(equipment).getViewName());
                            }
                        }
                    }
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

    private void handleActions() {
        addItemListener(e -> {
            if (getSelectedItem() != null && !getSelectedItem().equals(newItemString)) {
                removeItem(null);
                nullItem = false;
            }

            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (getSelectedIndex() != 0) {
                    selectedIndex = getSelectedIndex();
                    if (nullItem) {
                        selectedIndex = 0;
                    }
                }
            }
        });

        addActionListener(e -> {
            if (getSelectedItem() != null && getSelectedItem().equals(newItemString)) {
                List<String> oldList = new ArrayList<>(content);

                switch (boxType) {
                    case EQUIPMENT -> new EquipmentRowEditor(equipmentType, equipment, null);
                    case CALIBRATION -> new LibraryRowEditor(null, equipment, calibrationLibrary, null, appConfig, calibrationType);
                }
                updateData();
                setSelectedIndex(selectedIndex);

                if (oldList.size() != content.size()) {
                    removeItem(null);
                    setSelectedItem(findNewItem(oldList));
                }
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

    public EquipmentItem getSelectedEquipmentItem() {
        return equipment.getItemFromViewName(Objects.requireNonNull(getSelectedItem()).toString());
    }
}
