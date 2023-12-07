package models.equipment;

public class Flattener extends EquipmentItem {
    private double factor;

    public Flattener() {}

    public Flattener(String brand, String name) {
        super.setBrand(brand);
        super.setName(name);
    }

    public double getFactor() {
        return factor;
    }
    public void setFactor(double factor) {
        this.factor = factor;
    }
}
