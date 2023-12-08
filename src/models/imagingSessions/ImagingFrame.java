package models.imagingSessions;

public abstract class ImagingFrame {
    private String date;
    private int totalSubs;

    public ImagingFrame() {}

    public ImagingFrame(String date, int totalSubs) {
        this.date = date;
        this.totalSubs = totalSubs;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(int totalSubs) {
        this.totalSubs = totalSubs;
    }
}
