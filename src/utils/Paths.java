package utils;

import java.io.File;

public class Paths {
    private static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator;

    // folder paths:
    public static final String DATA_PATH = PROJECT_PATH + "data" + File.separator;
    public static final String BACKUP_PATH = DATA_PATH + "backup" + File.separator;
    public static final String IMAGE_PATH = DATA_PATH + "images" + File.separator;
    public static final String LOG_PATH  = DATA_PATH + "log" + File.separator + "app" + File.separator;

    // file paths:
    public static final String CALIBRATION_LIBRARY_PATH = DATA_PATH + "calibrationLibrary.json";
    public static final String CONFIGURATION_PATH = DATA_PATH + "configuration.json";
    public static final String EQUIPMENT_PATH = DATA_PATH + "equipment.json";
    public static final String IMAGING_PROJECTS_PATH = DATA_PATH + "imagingProjects.json";
    public static final String IMAGING_SESSIONS_PATH = DATA_PATH + "imagingSessions.json";
    public static final String LICENCE_PATH = DATA_PATH + "licence.txt";
}
