package models.imagingFrames;

import java.util.List;

/**
 * Used for jackson only.
 */
public class ImagingFrameListWrapper {
    private List<LightFrame> lightFrames;
    private List<DarkFrame> darkFrames;
    private List<BiasFrame> biasFrames;
    private List<FlatFrame> flatFrames;

    public List<LightFrame> getLightFrames() {
        return lightFrames;
    }
    public void setLightFrames(List<LightFrame> lightFrames) {
        this.lightFrames = lightFrames;
    }

    public List<DarkFrame> getDarkFrames() {
        return darkFrames;
    }
    public void setDarkFrames(List<DarkFrame> darkFrames) {
        this.darkFrames = darkFrames;
    }

    public List<BiasFrame> getBiasFrames() {
        return biasFrames;
    }
    public void setBiasFrames(List<BiasFrame> biasFrames) {
        this.biasFrames = biasFrames;
    }

    public List<FlatFrame> getFlatFrames() {
        return flatFrames;
    }
    public void setFlatFrames(List<FlatFrame> flatFrames) {
        this.flatFrames = flatFrames;
    }
}
