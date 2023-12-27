package models.imagingSessions;

public class DarkFrame extends ImagingFrame {
    private double cameraTemp;

    public DarkFrame() {}

    public DarkFrame(String date, Double totalSubs) {
        super(date, totalSubs);
    }

    public double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }
}
