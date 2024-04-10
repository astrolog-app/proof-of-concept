package ui.customComponents;

import models.imagingFrames.CalibrationFrame;
import models.imagingFrames.CalibrationType;
import models.equipment.*;
import models.imagingFrames.ImagingFrameList;
import models.settings.AppConfig;
import ui.popUps.NewBrand;
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
        CALIBRATION,
        BRAND
    }
    private final EquipmentType equipmentType;
    private final CalibrationType calibrationType;
    private final Equipment equipment;
    private final BoxType boxType;
    private final AppConfig appConfig;
    private final ImagingFrameList imagingFrameList;
    private final String newItemString = "Add New...";
    private final List<String> content = new ArrayList<>();
    private boolean nullItem = true;
    private int selectedIndex = 0;
    private String newBrand = "";

    public CustomComboBox(EquipmentType equipmentType, Equipment equipment) {
        this.equipmentType = equipmentType;
        this.calibrationType = null;
        this.equipment = equipment;
        this.boxType = BoxType.EQUIPMENT;
        this.appConfig = null;
        this.imagingFrameList = null;

        init();
    }

    public CustomComboBox(CalibrationType calibrationType, Equipment equipment, AppConfig appConfig, ImagingFrameList imagingFrameList) {
        this.equipmentType = null;
        this.calibrationType = calibrationType;
        this.equipment = equipment;
        this.boxType = BoxType.CALIBRATION;
        this.appConfig = appConfig;
        this.imagingFrameList = imagingFrameList;

        init();
    }

    public CustomComboBox(Equipment equipment) {
        this.equipmentType = null;
        this.calibrationType = null;
        this.equipment = equipment;
        this.boxType = BoxType.BRAND;
        this.appConfig = null;
        this.imagingFrameList = null;

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

        if (!newBrand.isEmpty()) {
            content.add(newBrand);
        }

        switch (boxType) {
            case EQUIPMENT -> {
                switch (equipmentType) {
                    case TELESCOPE -> equipment.getTelescopes().values().forEach(t -> content.add(t.getViewName()));
                    case MOUNT -> equipment.getMounts().values().forEach(m -> content.add(m.getViewName()));
                    case CAMERA -> equipment.getCameras().values().forEach(c -> content.add(c.getViewName()));
                    case FILTER -> equipment.getFilters().values().forEach(f -> content.add(f.getViewName()));
                    case FLATTENER -> equipment.getFlatteners().values().forEach(f -> content.add(f.getViewName()));
                }
            }
            case CALIBRATION -> {
                switch (calibrationType) {
                    case BIAS -> {
                        for (CalibrationFrame f : imagingFrameList.getCalibrationFrames()) {
                            if (f.getCalibrationType() == CalibrationType.BIAS) {
                                content.add(f.getCamera(equipment).getViewName());
                            }
                        }
                    }
                    case DARK -> {
                        for (CalibrationFrame f : imagingFrameList.getCalibrationFrames()) {
                            if (f.getCalibrationType() == CalibrationType.DARK) {
                                content.add(f.getCamera(equipment).getViewName());
                            }
                        }
                    }
                }
            }
            case BRAND -> content.addAll(equipment.getAllBrands());
        }

        content.sort(String.CASE_INSENSITIVE_ORDER);

        if (nullItem) {
            addItem(null);
        }
        addItem(newItemString);

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
                    case EQUIPMENT -> SwingUtilities.invokeLater(() -> new EquipmentRowEditor(equipmentType, equipment, null, null));
                    case CALIBRATION -> SwingUtilities.invokeLater(() -> new LibraryRowEditor(null, equipment, imagingFrameList, null, appConfig, calibrationType));
                    case BRAND -> SwingUtilities.invokeLater(() -> new NewBrand(this));
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

    public void selectNewBrand(String newBrand) {
        this.newBrand = newBrand;
        updateData();
        setSelectedItem(newBrand);
    }
}
