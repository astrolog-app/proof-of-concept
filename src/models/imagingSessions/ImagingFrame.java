package models.imagingSessions;

public class ImagingFrame {
    private String date;
    private Integer totalSubs;
    private Double subLength;

    public ImagingFrame() {}

    public ImagingFrame(String date, Integer totalSubs, Double subLength) {
        this.date = date;
        this.totalSubs = totalSubs;
        this.subLength = subLength;
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

    public Double getSubLength() {
        return subLength;
    }
    public void setSubLength(Double subLength) {
        this.subLength = subLength;
    }
}
