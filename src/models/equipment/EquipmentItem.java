package models.equipment;

import java.util.UUID;

public abstract class EquipmentItem {
    private UUID id;
    private boolean used;
    private String brand;
    private String name;

    public EquipmentItem() {}

    public EquipmentItem(UUID id, boolean used, String brand, String name) {
        this.id = id;
        this.used = used;
        this.brand = brand;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getUsed() {
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

    public abstract String[] getPropertyNames();

    public abstract String[] getProperties();
}
