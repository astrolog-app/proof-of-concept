package models.settings;

public enum LoggerColumns {
    DATE("Date"),
    TARGET("Target"),
    SUB_LENGTH("Sub Length"),
    TOTAL_SUBS("Total Subs"),
    TOTAL_EXPOSURE("Total Exposure"),
    INTEGRATED_SUBS("Integrated Subs"),
    INTEGRATED_EXPOSURE("Integrated Exposure"),
    FILTER("Filter"),
    GAIN("Gain"),
    OFFSET("Offset"),
    CAMERA_TEMP("Camera Temp"),
    OUTSIDE_TEMP("Outside Temp"),
    AVERAGE_SEEING("Average Seeing"),
    AVERAGE_CLOUD_COVER("Average Cloud Cover"),
    AVERAGE_MOON("Average Moon"),
    TELESCOPE("Telescope"),
    FLATTENER("Flattener"),
    MOUNT("Mount"),
    CAMERA("Camera"),
    NOTES("Notes");
    private final String name;

    LoggerColumns(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
