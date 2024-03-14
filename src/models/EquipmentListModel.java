package models;

import models.equipment.Camera;
import models.equipment.Equipment;
import models.equipment.Telescope;

import javax.swing.*;
import java.util.*;

public class EquipmentListModel extends AbstractListModel<String> {
    private final List<String> finalList = new ArrayList<>();
    private final List<String> cameras = new ArrayList<>();
    private final List<String> telescopes = new ArrayList<>();

    public EquipmentListModel(Equipment equipment) {
        for (Camera c : equipment.getCameras().values().stream().toList()) {
            cameras.add(c.getViewName());
        }
        Collections.sort(cameras);

        for (Telescope t : equipment.getTelescopes().values().stream().toList()) {
            telescopes.add(t.getViewName());
        }
        Collections.sort(telescopes);

        buildFinalList();
    }

    private void buildFinalList() {
        boolean first = true;
        finalList.clear();

        if (!cameras.isEmpty()) {
            finalList.add("Cameras");
            finalList.addAll(cameras);
            first = false;
        }

        if (!first)
            finalList.add(" ");

        if (!telescopes.isEmpty()) {
            finalList.add("Telescopes");
            finalList.addAll(telescopes);
            first = false;
        }
    }

    @Override
    public int getSize() {
        return finalList.size();
    }

    @Override
    public String getElementAt(int index) {
        return finalList.get(index);
    }
}
