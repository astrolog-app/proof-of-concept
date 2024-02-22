package models.calibrationFrames;

import models.imagingSessions.ImagingFrame;

public class DarkFrame extends ImagingFrame {
    private double cameraTemp;

    public DarkFrame() {}

    public DarkFrame(String date, Integer totalSubs, Double subLength) {
        super(date, totalSubs, subLength);
    }

    public double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }
}
