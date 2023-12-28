package models.equipment;

public class Flattener extends EquipmentItem {
    private double factor;

    public Flattener() {}

    public Flattener(boolean used, String brand, String name) {
        super(used, brand, name);
    }

    public double getFactor() {
        return factor;
    }
    public void setFactor(double factor) {
        this.factor = factor;
    }
}
