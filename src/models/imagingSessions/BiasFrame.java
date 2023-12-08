package models.imagingSessions;

public class BiasFrame extends ImagingFrame {
    private double subLength;

    public BiasFrame() {}

    public BiasFrame(String date, int totalSubs) {
        super(date, totalSubs);
    }

    public double getSubLength() {
        return subLength;
    }
    public void setSubLength(double subLength) {
        this.subLength = subLength;
    }
}
