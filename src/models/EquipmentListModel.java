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

    private State state;
    private final List<EquipmentItem> equipmentItemList;
    private List<EquipmentItem> itemList;

    public EquipmentListModel(Equipment equipment, State state) {
        this.state = state;

        equipmentItemList = equipment.createEquipmentItemList();
        setItemList();
    }

    private void setItemList() {
        switch (state) {
            case USED -> itemList = equipmentItemList.stream()
                    .filter(EquipmentItem::getUsed)
                    .toList();
            case UNUSED -> itemList = equipmentItemList.stream()
                    .filter(item -> !item.getUsed())
                    .toList();
            case ALL -> itemList = equipmentItemList;
        }
    }

    @Override
    public int getSize() {
        return itemList.size();
    }

    @Override
    public String getElementAt(int index) {
        return itemList.get(index).getViewName();
    }

    public void changeState (State state) {
        this.state = state;
        setItemList();
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
