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

    public static CalibrationType getEnum(String name) {
        for (CalibrationType calibrationType : CalibrationType.values()) {
            if (calibrationType.getName().equalsIgnoreCase(name)) {
                return calibrationType;
            }
        }

        return null;
    }
}
