package models.imagingSessions;

public class LightFrame {
    private String date;
    private String target;
    private int subLength;
    private int totalSubs;
    private int integratedSubs;
    private String filter;
    private int gain;
    private int offset;
    private int cameraTemp;
    private int outsideTemp;
    private int averageSeeing;
    private int averageCloudCover;
    private int averageMoon;
    private String telescope;
    private String flattener;
    private String camera;
    private String notes;

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

    public int getSubLength() {
        return subLength;
    }
    public void setSubLength(int subLength) {
        this.subLength = subLength;
    }

    public int getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(int totalSubs) {
        this.totalSubs = totalSubs;
    }

    public int getIntegratedSubs() {
        return integratedSubs;
    }
    public void setIntegratedSubs(int integratedSubs) {
        this.integratedSubs = integratedSubs;
    }

    public String getFilter() {
        return filter;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getGain() {
        return gain;
    }
    public void setGain(int gain) {
        this.gain = gain;
    }

    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(int cameraTemp) {
        this.cameraTemp = cameraTemp;
    }

    public int getOutsideTemp() {
        return outsideTemp;
    }
    public void setOutsideTemp(int outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public int getAverageSeeing() {
        return averageSeeing;
    }
    public void setAverageSeeing(int averageSeeing) {
        this.averageSeeing = averageSeeing;
    }

    public int getAverageCloudCover() {
        return averageCloudCover;
    }
    public void setAverageCloudCover(int averageCloudCover) {
        this.averageCloudCover = averageCloudCover;
    }

    public int getAverageMoon() {
        return averageMoon;
    }
    public void setAverageMoon(int averageMoon) {
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
