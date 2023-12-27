package models.imagingSessions;

public class LightFrame extends ImagingFrame {
    private String target;
    private Double subLength;
    private Double integratedSubs;
    private String filter;
    private Double gain;
    private Double offset;
    private Double cameraTemp;
    private Double outsideTemp;
    private Double averageSeeing;
    private Double averageCloudCover;
    private Double averageMoon;
    private String telescope;
    private String flattener;
    private String camera;
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

    public String getFilter() {
        return filter;
    }
    public void setFilter(String filter) {
        this.filter = filter;
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

    public String getTelescope() {
        return telescope;
    }
    public void setTelescope(String telescope) {
        this.telescope = telescope;
    }

    public String getFlattener() {
        return flattener;
    }
    public void setFlattener(String flattener) {
        this.flattener = flattener;
    }

    public String getCamera() {
        return camera;
    }
    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
