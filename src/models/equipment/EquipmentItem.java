package models.equipment;

public abstract class EquipmentItem {
    private boolean used;
    private String brand;
    private String name;

    public EquipmentItem() {}

    public EquipmentItem(boolean used, String brand, String name) {
        this.used = used;
        this.brand = brand;
        this.name = name;
    }

    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
