package models.imagingFrames;

import java.util.UUID;

public class BiasFrame extends CalibrationFrame {
    public BiasFrame() {}

    public BiasFrame(Integer totalSubs, Double subLength, UUID id, UUID cameraId, Integer gain, String path) {
        super(totalSubs, subLength, id, cameraId, CalibrationType.BIAS, gain, path);
    }
}
