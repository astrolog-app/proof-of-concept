package models.imagingSessions.calibrationFrames;

import models.imagingSessions.ImagingFrame;

import java.util.UUID;

public class DarkFrame extends ImagingFrame {
    private double cameraTemp;

    public DarkFrame() {}

    public DarkFrame(UUID id, UUID cameraId, String date, Integer totalSubs, Double subLength, Integer gain) {
        super(id, cameraId, date, totalSubs, subLength, gain);
    }

    public double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }
}
