package models.equipment;

public class Filter extends EquipmentItem {
    private String bandType;

    public Filter() {}

    public Filter(String brand, String name) {
        super.setBrand(brand);
        super.setName(name);
    }

    public String getBandType() {
        return bandType;
    }
    public void setBandType(String bandType) {
        this.bandType = bandType;
    }
}
