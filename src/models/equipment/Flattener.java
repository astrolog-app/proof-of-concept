package models.equipment;

import java.util.UUID;

public class Flattener extends EquipmentItem {
    private double factor;

    public Flattener() {}

    public Flattener(UUID id, boolean used, String brand, String name) {
        super(id, used, brand, name);
    }

    public double getFactor() {
        return factor;
    }
    public void setFactor(double factor) {
        this.factor = factor;
    }

    @Override
    public String[] getParams() {
        return new String[]{Double.toString(getFactor())};
    }
}
