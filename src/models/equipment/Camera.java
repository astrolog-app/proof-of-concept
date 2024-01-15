package models.equipment;

import java.util.UUID;

public class Camera extends EquipmentItem {
    private String chipSize;
    private int megaPixel;
    private boolean rgb;

    public Camera() {}

    public Camera(UUID id, boolean used, String name, String brand, String chipSize, int megaPixel, boolean rgb) {
        super(id, used, brand, name);
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

    @Override
    public String[] getProperties() {
        return new String[]{getChipSize(), Integer.toString(getMegaPixel()), String.valueOf(getRgb())};
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{"Chip Size", "Mega Pixel", "Color"};
    }
}
