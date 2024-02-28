package models.imagingFrames;

import java.util.*;

public class ImagingFrameList {
    private Map<UUID, LightFrame> lightFrames = new HashMap<>();
    private Map<UUID, DarkFrame> darkFrames = new HashMap<>();
    private Map<UUID, BiasFrame> biasFrames = new HashMap<>();
    private Map<UUID, FlatFrame> flatFrames = new HashMap<>();

    public Map<UUID, LightFrame> getLightFrames() {
        return lightFrames;
    }
    public void setLightFrames(Map<UUID, LightFrame> lightFrames) {
        this.lightFrames = lightFrames;
    }
    public void addLightFrame(LightFrame lightFrame) {
        lightFrames.put(lightFrame.getId(), lightFrame);
    }
    public void removeLightFrame(LightFrame lightFrame) {
        lightFrames.remove(lightFrame.getId(), lightFrame);
    }

    public Map<UUID, DarkFrame> getDarkFrames() {
        return darkFrames;
    }
    public void setDarkFrames(Map<UUID, DarkFrame> darkFrames) {
        this.darkFrames = darkFrames;
    }
    public void addDarkFrame(DarkFrame darkFrame) {
        darkFrames.put(darkFrame.getId(), darkFrame);
    }
    public void removeDarkFrame(DarkFrame darkFrame) {
        darkFrames.remove(darkFrame.getId(), darkFrame);
    }

    public Map<UUID, BiasFrame> getBiasFrames() {
        return biasFrames;
    }
    public void setBiasFrames(Map<UUID, BiasFrame> biasFrames) {
        this.biasFrames = biasFrames;
    }
    public void addBiasFrame(BiasFrame biasFrame) {
        biasFrames.put(biasFrame.getId(), biasFrame);
    }
    public void removeBiasFrame(BiasFrame biasFrame) {
        biasFrames.remove(biasFrame.getId(), biasFrame);
    }

    public Map<UUID, FlatFrame> getFlatFrames() {
        return flatFrames;
    }
    public void setFlatFrames(Map<UUID, FlatFrame> flatFrames) {
        this.flatFrames = flatFrames;
    }
    public void addFlatFrame(FlatFrame flatFrame) {
        flatFrames.put(flatFrame.getId(), flatFrame);
    }
    public void removeFlatFrame(FlatFrame flatFrame) {
        flatFrames.remove(flatFrame.getId(), flatFrame);
    }

    public List<CalibrationFrame> getCalibrationFrames() {
        List<CalibrationFrame> l = new ArrayList<>();

        l.addAll(darkFrames.values());
        l.addAll(biasFrames.values());

        return l;
    }
    public void removeCalibrationFrame(CalibrationFrame calibrationFrame, CalibrationType calibrationType) {
        switch (calibrationType) {
            case DARK -> removeDarkFrame((DarkFrame) calibrationFrame);
            case BIAS -> removeBiasFrame((BiasFrame) calibrationFrame);
        }
    }
}
