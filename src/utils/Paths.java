package utils;

import java.io.File;

public class Paths {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String DATA_PATH = (PROJECT_PATH + File.separator + "data" + File.separator);
    public static final String CONFIGURATION_PATH = DATA_PATH + "configuration.json";
    public static final String EQUIPMENT_PATH = DATA_PATH + "equipment.json";
    public static final String IMAGING_SESSIONS_PATH = DATA_PATH + "imagingSessions.json";
    public static final String IMAGE_PATH = DATA_PATH + "images" + File.separator;
    public static final String LOG_PATH  = DATA_PATH + "log" + File.separator;
}
