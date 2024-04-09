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

    private final Equipment equipment;
    private State state;
    private List<EquipmentItem> itemList;

    public EquipmentListModel(Equipment equipment, State state) {
        this.equipment = equipment;
        this.state = state;

        setItemList();
    }

    private void setItemList() {
        switch (state) {
            case USED -> itemList = equipment.createEquipmentItemList().stream()
                    .filter(EquipmentItem::getUsed)
                    .toList();
            case UNUSED -> itemList = equipment.createEquipmentItemList().stream()
                    .filter(item -> !item.getUsed())
                    .toList();
            case ALL -> itemList = equipment.createEquipmentItemList();
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
        update();
    }

    public void update() {
        setItemList();
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
