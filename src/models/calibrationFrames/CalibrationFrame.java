package models.calibrationFrames;

import models.equipment.Camera;
import models.equipment.Equipment;
import models.imagingSessions.ImagingFrame;

import java.util.UUID;

public class CalibrationFrame extends ImagingFrame {
    private UUID id;
    private UUID cameraId;
    private CalibrationType calibrationType;
    private Integer gain;
    private String path;

    public CalibrationFrame() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public Camera getCamera(Equipment equipment) {
        return equipment.getCameras().get(cameraId);
    }
    public UUID getCameraId() {
        return cameraId;
    }
    public void setCameraId(UUID cameraId) {
        this.cameraId = cameraId;
    }

    public CalibrationType getCalibrationType() {
        return calibrationType;
    }
    public void setCalibrationType(CalibrationType calibrationType) {
        this.calibrationType = calibrationType;
    }

    public Integer getGain() {
        return gain;
    }
    public void setGain(Integer gain) {
        this.gain = gain;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
