package models.equipment;

import java.util.UUID;

public class Telescope extends EquipmentItem {
    private int focalLength;
    private int aperture;

    public Telescope() {}

    public Telescope(UUID id, boolean used, String name, String brand, int focalLength, int aperture) {
        super(id, used, brand, name);
        this.focalLength = focalLength;
        this.aperture = aperture;
    }

    public int getFocalLength() {
        return focalLength;
    }
    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public int getAperture() {
        return aperture;
    }
    public void setAperture(int aperture) {
        this.aperture = aperture;
    }

    @Override
    public String[] getParams() {
        return new String[]{Integer.toString(getFocalLength()), Integer.toString(getAperture())};
    }
}
