package models.imagingFrames;

import models.equipment.*;

import java.util.UUID;

public class LightFrame extends ImagingFrame {
    private String date;
    private String target;
    private Integer integratedSubs;
    private UUID filterID;
    private Double offset;
    private Double cameraTemp;
    private Double outsideTemp;
    private Double averageSeeing;
    private Double averageCloudCover;
    private Double averageMoon;
    private UUID telescopeID;
    private UUID flattenerID;
    private UUID mountID;
    private String notes;
    private Double subLength;

    public LightFrame() {}

    public LightFrame(String date, UUID cameraId, Integer totalSubs, Double subLength, Integer gain) {
        super(null, cameraId,  totalSubs, gain);
        this.date = date;
        this.subLength = subLength;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getIntegratedSubs() {
        return integratedSubs;
    }
    public void setIntegratedSubs(Integer integratedSubs) {
        this.integratedSubs = integratedSubs;
    }

    public Filter getFilter(Equipment equipment) {
        return equipment.getFilters().get(filterID);
    }
    public void setFilterID(UUID filterID) {
        this.filterID = filterID;
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
        return equipment.getTelescopes().get(telescopeID);
    }
    public void setTelescopeID(UUID telescopeID) {
        this.telescopeID = telescopeID;
    }

    public Flattener getFlattener(Equipment equipment) {
        return equipment.getFlatteners().get(flattenerID);
    }
    public void setFlattenerID(UUID flattenerID) {
        this.flattenerID = flattenerID;
    }

    public Mount getMount(Equipment equipment) {
        return equipment.getMounts().get(mountID);
    }
    public void setMountID(UUID mountID) {
        this.mountID = mountID;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getSubLength() {
        return subLength;
    }
    public void setSubLength(Double subLength) {
        this.subLength = subLength;
    }
}
