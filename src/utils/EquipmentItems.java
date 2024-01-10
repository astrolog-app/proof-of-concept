package utils;

import models.equipment.EquipmentItem;

public class EquipmentItems {
    public static String constructName(EquipmentItem equipmentItem) {
        if (equipmentItem != null) {
            return equipmentItem.getBrand() + " " + equipmentItem.getName();
        }

        return null;
    }
}
