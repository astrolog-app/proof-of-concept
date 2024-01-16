package models.equipment;

import java.util.UUID;

public class Flattener extends EquipmentItem {
    private double factor;

    public Flattener() {}

    public Flattener(UUID id, boolean used, String brand, String name, double factor) {
        super(id, used, brand, name);
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
    public void setFactor(double factor) {
        this.factor = factor;
    }

    @Override
    public String[] getProperties() {
        return new String[]{Double.toString(getFactor())};
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{"Factor"};
    }
}
