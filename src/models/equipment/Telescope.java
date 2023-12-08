package models.equipment;

public class Telescope extends EquipmentItem {
    private int focalLength;
    private int aperture;

    public Telescope() {}

    public Telescope(String name, String brand, int focalLength, int aperture) {
        super(brand, name);
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
}
