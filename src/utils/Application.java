package utils;

import services.AppLogger;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {
    private static final Logger logger = AppLogger.getLogger();
    public static String VERSION = "0.9.0";

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            logger.severe("failed to execute Thread.sleep");
        }
    }

    public static void update() {
        String command; // path to the executable file

        // Determine OS and set command accordingly
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            command = Paths.PROJECT_PATH + "update.exe"; // Windows path
        } else {
            command = Paths.PROJECT_PATH + "update"; // Linux or macOS path
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // Redirects the process's standard output and error streams to the Java process.
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Executable executed successfully");
            } else {
                logger.severe("Executable execution failed with error code: " + exitCode);
            }

            System.exit(0);
        } catch (IOException | InterruptedException e) {
            logger.severe("couldn't update application:" + "\t" + e.getMessage());
        }
    }
}
