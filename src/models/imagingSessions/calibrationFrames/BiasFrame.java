package models.imagingSessions.calibrationFrames;

import models.imagingSessions.ImagingFrame;

import java.util.UUID;

public class BiasFrame extends ImagingFrame {
    public BiasFrame() {}

    public BiasFrame(UUID id, UUID cameraId, String date, Integer totalSubs, Double subLength, Integer gain) {
        super(id, cameraId, date, totalSubs, subLength, gain);
    }
}
