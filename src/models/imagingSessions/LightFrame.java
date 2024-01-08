package models.imagingSessions;

import models.equipment.*;

import java.util.UUID;

public class LightFrame extends ImagingFrame {
    private String target;
    private Double subLength;
    private Double integratedSubs;
    private UUID filterID;
    private Double gain;
    private Double offset;
    private Double cameraTemp;
    private Double outsideTemp;
    private Double averageSeeing;
    private Double averageCloudCover;
    private Double averageMoon;
    private UUID telescopeID;
    private UUID flattenerID;
    private UUID mountID;
    private UUID cameraID;
    private String notes;

    public LightFrame() {}

    public LightFrame(String date, Double totalSubs) {
        super(date, totalSubs);
    }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public Double getSubLength() {
        return subLength;
    }
    public void setSubLength(Double subLength) {
        this.subLength = subLength;
    }

    public Double getIntegratedSubs() {
        return integratedSubs;
    }
    public void setIntegratedSubs(Double integratedSubs) {
        this.integratedSubs = integratedSubs;
    }

    public Filter getFilter(Equipment equipment) {
        for (Filter f : equipment.getFilters()) {
            if (f.getId().equals(filterID)) {
                return f;
            }
        }

        return null;
    }
    public void setFilterID(UUID filterID) {
        this.filterID = filterID;
    }

    public Double getGain() {
        return gain;
    }
    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Double getOffset() {
        return offset;
    }
    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public Double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(Double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }

    public Double getOutsideTemp() {
        return outsideTemp;
    }
    public void setOutsideTemp(Double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public Double getAverageSeeing() {
        return averageSeeing;
    }
    public void setAverageSeeing(Double averageSeeing) {
        this.averageSeeing = averageSeeing;
    }

    public Double getAverageCloudCover() {
        return averageCloudCover;
    }
    public void setAverageCloudCover(Double averageCloudCover) {
        this.averageCloudCover = averageCloudCover;
    }

    public Double getAverageMoon() {
        return averageMoon;
    }
    public void setAverageMoon(Double averageMoon) {
        this.averageMoon = averageMoon;
    }

    public Telescope getTelescope(Equipment equipment) {
        for (Telescope t : equipment.getTelescopes()) {
            if (t.getId().equals(telescopeID)) {
                return t;
            }
        }

        return null;
    }
    public void setTelescopeID(UUID telescopeID) {
        this.telescopeID = telescopeID;
    }

    public Flattener getFlattener(Equipment equipment) {
        for (Flattener f : equipment.getFlatteners()) {
            if (f.getId().equals(flattenerID)) {
                return f;
            }
        }

        return null;
    }
    public void setFlattenerID(UUID flattenerID) {
        this.flattenerID = flattenerID;
    }

    public Mount getMount(Equipment equipment) {
        for (Mount m : equipment.getMounts()) {
            if (m.getId().equals(mountID)) {
                return m;
            }
        }

        return null;
    }
    public void setMountID(UUID mountID) {
        this.mountID = mountID;
    }

    public Camera getCamera(Equipment equipment) {
        for (Camera c : equipment.getCameras()) {
            if (c.getId().equals(cameraID)) {
                return c;
            }
        }

        return null;
    }
    public void setCameraID(UUID cameraID) {
        this.cameraID = cameraID;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
