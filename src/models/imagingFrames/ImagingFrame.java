package models.imagingFrames;

import models.equipment.Camera;
import models.equipment.Equipment;

import java.util.UUID;

public class ImagingFrame {
    private UUID id;
    private UUID cameraId;
    private Integer totalSubs;
    private Double subLength;
    private Integer gain;

    public ImagingFrame() {
        id = UUID.randomUUID();
    }

    public ImagingFrame(UUID id, UUID cameraId, Integer totalSubs, Double subLength, Integer gain) {
        this.id = id;
        this.cameraId = cameraId;
        this.totalSubs = totalSubs;
        this.subLength = subLength;
        this.gain = gain;
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

    public Integer getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(Integer totalSubs) {
        this.totalSubs = totalSubs;
    }

    public Double getSubLength() {
        return subLength;
    }
    public void setSubLength(Double subLength) {
        this.subLength = subLength;
    }

    public Integer getGain() {
        return gain;
    }
    public void setGain(Integer gain) {
        this.gain = gain;
    }
}
