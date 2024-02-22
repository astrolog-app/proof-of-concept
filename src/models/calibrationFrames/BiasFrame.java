package models.calibrationFrames;

import models.imagingSessions.ImagingFrame;

public class BiasFrame extends ImagingFrame {
    private double subLength;

    public BiasFrame() {}

    public BiasFrame(String date, Integer totalSubs) {
        super(date, totalSubs);
    }

    public double getSubLength() {
        return subLength;
    }
    public void setSubLength(double subLength) {
        this.subLength = subLength;
    }
}
