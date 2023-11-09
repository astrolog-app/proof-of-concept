package models;

public class Path {
    private static final String projectPath = System.getProperty("user.dir");
    public static final String configurationPath = (
            projectPath.replace("/AstroLogger-code", "") + "/AstroLogger-app") + "/configuration.json";
}
