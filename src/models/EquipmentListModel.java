package models;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;

import javax.swing.*;
import java.util.*;

public class EquipmentListModel extends AbstractListModel<String> {
    public enum State {
        USED,
        UNUSED,
        ALL
    }
    private final State state;
    private final List<EquipmentItem> equipmentItemList;

    public EquipmentListModel(Equipment equipment, State state) {
        this.state = state;

        equipmentItemList = equipment.createEquipmentItemList();
    }

    @Override
    public int getSize() {
        return equipmentItemList.size();
    }

    @Override
    public String getElementAt(int index) {
        return equipmentItemList.get(index).getViewName();
    }
}
