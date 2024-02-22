package models.imagingSessions;

import java.util.UUID;

public class FlatFrame extends ImagingFrame {
    public FlatFrame() {}

    public FlatFrame(UUID id, UUID cameraId, String date, Integer totalSubs, Double subLength, Integer gain) {
        super(id, cameraId, date, totalSubs, subLength, gain);
    }
}
