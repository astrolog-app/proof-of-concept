package models.imagingSessions;

public class FlatFrame {
    private String date;
    private double totalSubs;
    private double subLength;

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

    public double getSubLength() {
        return subLength;
    }
    public void setSubLength(double subLength) {
        this.subLength = subLength;
    }
}
