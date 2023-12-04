package models.equipment;

public class Camera implements EquipmentItem {
    private String name;
    private String brand;
    private String chipSize;
    private int megaPixel;
    private boolean rgb;


    public Camera(String name, String brand, String chipSize, int megaPixel, boolean rgb) {
        this.name = name;
        this.brand = brand;
        this.chipSize = chipSize;
        this.megaPixel = megaPixel;
        this.rgb = rgb;
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

    public String getChipSize() {
        return chipSize;
    }
    public void setChipSize(String chipSize) {
        this.chipSize = chipSize;
    }

    public int getMegaPixel() {
        return megaPixel;
    }
    public void setMegaPixel(int megaPixel) {
        this.megaPixel = megaPixel;
    }

    public boolean getRgb() {
        return rgb;
    }
    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }
}
