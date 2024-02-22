package models.imagingFrames;

import java.util.UUID;

public class DarkFrame extends CalibrationFrame {
    private Double cameraTemp;

    public DarkFrame() {}

    public DarkFrame(String date, Integer totalSubs, Double subLength, UUID id, UUID cameraId,
                     Integer gain, String path, Double cameraTemp) {
        super(date, totalSubs, subLength, id, cameraId, CalibrationType.DARK, gain, path);
        this.cameraTemp = cameraTemp;
    }

    public Double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(Double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }
}
