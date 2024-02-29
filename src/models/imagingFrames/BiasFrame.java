package models.imagingFrames;

import java.util.UUID;

public class BiasFrame extends CalibrationFrame {
    public BiasFrame() {}

    public BiasFrame(Integer totalSubs, UUID id, UUID cameraId, Integer gain, String path) {
        super(totalSubs, id, cameraId, CalibrationType.BIAS, gain, path);
    }
}
