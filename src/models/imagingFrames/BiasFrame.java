package models.imagingFrames;

import java.util.UUID;

public class BiasFrame extends CalibrationFrame {
    public BiasFrame() {}

    public BiasFrame(String date, Integer totalSubs, Double subLength, UUID id, UUID cameraId, Integer gain, String path) {
        super(date, totalSubs, subLength, id, cameraId, CalibrationType.BIAS, gain, path);
    }
}
