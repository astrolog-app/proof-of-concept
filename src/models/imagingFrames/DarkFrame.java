package models.imagingFrames;

import java.util.UUID;

public class DarkFrame extends CalibrationFrame {
    private Double cameraTemp;
    private Double subLength;

    public DarkFrame() {}

    public DarkFrame(Integer totalSubs, Double subLength, UUID id, UUID cameraId,
                     Integer gain, String path, Double cameraTemp) {
        super(totalSubs, id, cameraId, CalibrationType.DARK, gain, path);
        this.cameraTemp = cameraTemp;
        this.subLength = subLength;
    }

    public Double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(Double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }

    public Double getSubLength() {
        return subLength;
    }
    public void setSubLength(Double subLength) {
        this.subLength = subLength;
    }
}
