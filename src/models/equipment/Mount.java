package models.equipment;

import java.util.UUID;

public class Mount extends EquipmentItem {
    public Mount() {}

    public Mount(UUID id, boolean used, String brand, String name) {
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
