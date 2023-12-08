package models.imagingSessions;

public class FlatFrame extends ImagingFrame {
    private double subLength;

    public FlatFrame() {}

    public FlatFrame(String date, int totalSubs) {
        super(date, totalSubs);
    }

    public double getSubLength() {
        return subLength;
    }
    public void setSubLength(double subLength) {
        this.subLength = subLength;
    }
}
