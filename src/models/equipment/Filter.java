package models.equipment;

public class Filter extends EquipmentItem {
    private String bandType;

    public Filter() {}

    public Filter(boolean used, String brand, String name) {
        super(used, brand, name);
    }

    public String getBandType() {
        return bandType;
    }
    public void setBandType(String bandType) {
        this.bandType = bandType;
    }
}
