package services;

import utils.Paths;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    public static Logger getLogger() {
        return logger;
    }

    public static void save() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of("UTC");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")
                .withZone(zoneId);
        String formattedDateTime = formatter.format(instant);

        deleteOldLogFiles();
        try {
            String logFilePath = Paths.LOG_PATH + formattedDateTime + ".log";
            FileHandler fileHandler = new FileHandler(logFilePath);

            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);

            logger.setLevel(Level.FINE);

            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up FileHandler", e);
        }
    }

    private static void deleteOldLogFiles() {
        String folderPath = Paths.LOG_PATH;
        File folder = new File(folderPath);

        File[] files = folder.listFiles();

        if (files != null && files.length > 19) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));

            File oldestFile = files[0];
            if (oldestFile.delete()) {
                logger.fine("Deleted the oldest file: " + oldestFile.getName());
            } else {
                logger.severe("Failed to delete the oldest file: " + oldestFile.getName());
            }
        }
    }
}
