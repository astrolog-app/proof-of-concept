package models.equipment;

public class Accessoire extends EquipmentItem {
    public Accessoire() {}

    public Accessoire(String brand, String name) {
        super.setBrand(brand);
        super.setName(name);
    }
}
