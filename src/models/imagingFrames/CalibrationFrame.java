package models.imagingFrames;

import java.util.UUID;

public class CalibrationFrame extends ImagingFrame {
    private CalibrationType calibrationType;
    private String path;

    public CalibrationFrame() {}

    public CalibrationFrame(String date, Integer totalSubs, Double subLength, UUID id, UUID cameraId,
                            CalibrationType calibrationType, Integer gain, String path) {
        super(id, cameraId, date, totalSubs, subLength, gain);
        this.calibrationType = calibrationType;
        this.path = path;
    }

    public CalibrationType getCalibrationType() {
        return calibrationType;
    }
    public void setCalibrationType(CalibrationType calibrationType) {
        this.calibrationType = calibrationType;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}