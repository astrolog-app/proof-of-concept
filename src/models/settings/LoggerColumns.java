package models.settings;

public enum LoggerColumns {
    DATE,
    TARGET,
    SUB_LENGTH,
    TOTAL_SUBS,
    TOTAL_EXPOSURE,
    INTEGRATED_SUBS,
    INTEGRATED_EXPOSURE,
    FILTER,
    GAIN,
    OFFSET,
    CAMERA_TEMP_C,
    CAMERA_TEMP_F,
    OUTSIDE_TEMP_C,
    OUTSIDE_TEMP_F,
    AVERAGE_SEEING,
    AVERAGE_CLOUD_COVER,
    AVERAGE_MOON,
    TELESCOPE,
    FLATTENER,
    CAMERA,
    NOTES;

    @Override
    public String toString() {
        String newEnum = name().toLowerCase();

        if (newEnum.contains("_")) {
            newEnum = newEnum.replace("_", " ");
        }

        return newEnum;
    }
}
