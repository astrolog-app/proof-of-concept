package models.calibrationFrames;

import models.imagingSessions.ImagingFrame;

public class BiasFrame extends ImagingFrame {
    public BiasFrame() {}

    public BiasFrame(String date, Integer totalSubs, Double subLength) {
        super(date, totalSubs, subLength);
    }
}
