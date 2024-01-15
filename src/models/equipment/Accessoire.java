package models.equipment;

import java.util.UUID;

public class Accessoire extends EquipmentItem {
    public Accessoire() {}

    public Accessoire(UUID id, boolean used, String brand, String name) {
        super(id, used, brand, name);
    }

    @Override
    public String[] getProperties() {
        return new String[]{};
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{};
    }
}
