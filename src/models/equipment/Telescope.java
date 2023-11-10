package models.equipment;

public class Telescope implements EquipmentItem {
    private String name;
    private String brand;
    private int focalLength;
    private int aperture;

    public Telescope(String name, String brand, int focalLength, int aperture) {
        this.name = name;
        this.brand = brand;
        this.focalLength = focalLength;
        this.aperture = aperture;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBrand() {
        return brand;
    }
    @Override
    public void setBrand(String brand) {
        this.brand = brand;
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
