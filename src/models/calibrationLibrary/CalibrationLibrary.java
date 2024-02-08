package models.calibrationLibrary;

import models.equipment.Camera;
import models.equipment.Equipment;

import java.util.UUID;

public class CalibrationLibrary {
    private UUID id;
    private UUID cameraId;
    private CalibrationType calibrationType;
    private Integer subLength;
    private Integer gain;
    private Integer totalSubs;
    private String path;

    public CalibrationLibrary() {
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

    public Integer getSubLength() {
        return subLength;
    }
    public void setSubLength(Integer subLength) {
        this.subLength = subLength;
    }

    public Integer getGain() {
        return gain;
    }
    public void setGain(Integer gain) {
        this.gain = gain;
    }

    public Integer getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(Integer totalSubs) {
        this.totalSubs = totalSubs;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
