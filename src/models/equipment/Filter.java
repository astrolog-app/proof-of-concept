package models.equipment;

import java.util.UUID;

public class Filter extends EquipmentItem {
    private String bandType;

    public Filter() {}

    public Filter(UUID id, boolean used, String brand, String name) {
        super(id, used, brand, name);
    }

    public String getBandType() {
        return bandType;
    }
    public void setBandType(String bandType) {
        this.bandType = bandType;
    }
}
