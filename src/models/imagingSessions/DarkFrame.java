package models.imagingSessions;

public class DarkFrame {
    private String date;
    private double totalSubs;
    private double cameraTemp;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(double totalSubs) {
        this.totalSubs = totalSubs;
    }

    public double getCameraTemp() {
        return cameraTemp;
    }
    public void setCameraTemp(double cameraTemp) {
        this.cameraTemp = cameraTemp;
    }
}
