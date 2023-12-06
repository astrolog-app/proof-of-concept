package models.imagingSessions;

public class ImagingSession {
    private LightFrame lightFrame;
    private DarkFrame darkFrame;
    private FlatFrame frame;
    private BiasFrame biasFrame;

    public LightFrame getLightFrame() {
        return lightFrame;
    }
    public void setLightFrame(LightFrame lightFrame) {
        this.lightFrame = lightFrame;
    }

    public DarkFrame getDarkFrame() {
        return darkFrame;
    }
    public void setDarkFrame(DarkFrame darkFrame) {
        this.darkFrame = darkFrame;
    }

    public FlatFrame getFrame() {
        return frame;
    }
    public void setFrame(FlatFrame frame) {
        this.frame = frame;
    }

    public BiasFrame getBiasFrame() {
        return biasFrame;
    }
    public void setBiasFrame(BiasFrame biasFrame) {
        this.biasFrame = biasFrame;
    }
}
