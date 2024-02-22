package models.imagingSessions;

public class ImagingFrame {
    private String date;
    private Integer totalSubs;

    public ImagingFrame() {}

    public ImagingFrame(String date, Integer totalSubs) {
        this.date = date;
        this.totalSubs = totalSubs;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotalSubs() {
        return totalSubs;
    }
    public void setTotalSubs(Integer totalSubs) {
        this.totalSubs = totalSubs;
    }
}
