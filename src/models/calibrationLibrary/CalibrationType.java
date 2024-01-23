package models.calibrationLibrary;

public enum CalibrationType {
    DARK("Dark"),
    BIAS("Bias");

    private final String name;

    CalibrationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
