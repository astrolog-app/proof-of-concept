package models.imagingSessions;

public class LightFrame extends ImagingFrame {
    private String target;
    private double subLength;
    private double integratedSubs;
    private String filter;
    private double gain;
    private double offset;
    private double cameraTemp;
    private double outsideTemp;
    private double averageSeeing;
    private double averageCloudCover;
    private double averageMoon;
    private String telescope;
    private String flattener;
    private String camera;
    private String notes;

    public LightFrame() {}

    public LightFrame(String date, int totalSubs) {
        super(date, totalSubs);
    }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public double getSubLength() {
        return subLength;
    }
    public void setSubLength(double subLength) {
        this.subLength = subLength;
    }

    public double getIntegratedSubs() {
        return integratedSubs;
    }
    public void setIntegratedSubs(double integratedSubs) {
        this.integratedSubs = integratedSubs;
    }

    public String getFilter() {
        return filter;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }

    public double getGain() {
        return gain;
    }
    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getOffset() {
        return offset;
    }
    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }

    public double getOutsideTemp() {
        return outsideTemp;
    }
    public void setOutsideTemp(double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public double getAverageSeeing() {
        return averageSeeing;
    }
    public void setAverageSeeing(double averageSeeing) {
        this.averageSeeing = averageSeeing;
    }

    public double getAverageCloudCover() {
        return averageCloudCover;
    }
    public void setAverageCloudCover(double averageCloudCover) {
        this.averageCloudCover = averageCloudCover;
    }

    public double getAverageMoon() {
        return averageMoon;
    }
    public void setAverageMoon(double averageMoon) {
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
