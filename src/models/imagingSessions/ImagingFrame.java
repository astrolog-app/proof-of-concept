package models.imagingSessions;

public class ImagingFrame {
    private String date;
    private Double totalSubs;

    public ImagingFrame() {}

    public ImagingFrame(String date, Double totalSubs) {
        this.date = date;
        this.totalSubs = totalSubs;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(Double totalSubs) {
        this.totalSubs = totalSubs;
    }
}
