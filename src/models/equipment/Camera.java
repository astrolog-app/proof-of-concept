package models.equipment;

public class Camera extends EquipmentItem {
    private String chipSize;
    private int megaPixel;
    private boolean rgb;

    public Camera() {}

    public Camera(boolean used, String name, String brand, String chipSize, int megaPixel, boolean rgb) {
        super(used, brand, name);
        this.chipSize = chipSize;
        this.megaPixel = megaPixel;
        this.rgb = rgb;
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
