package models.equipment;

public class Mount extends EquipmentItem {
    public Mount() {}

    public Mount(String brand, String name) {
        super.setBrand(brand);
        super.setName(name);
    }
}
