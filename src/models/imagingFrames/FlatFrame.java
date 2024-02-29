package models.imagingFrames;

import java.util.UUID;

public class FlatFrame extends ImagingFrame {
    public FlatFrame() {}

    public FlatFrame(UUID id, UUID cameraId, Integer totalSubs, Integer gain) {
        super(id, cameraId, totalSubs, gain);
    }
}
