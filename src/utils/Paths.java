package utils;

public class Paths {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String APP_PATH = (
            PROJECT_PATH.replace("/AstroLogger-code", "") + "/AstroLogger-app");
    public static final String CONFIGURATION_PATH = APP_PATH + "/configuration.json";
    public static final String IMAGE_PATH = APP_PATH + "/images";
}
