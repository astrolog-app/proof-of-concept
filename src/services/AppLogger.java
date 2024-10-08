package services;

import  utils.Paths;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.*;

public class AppLogger {
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    public static Logger getLogger() {
        return logger;
    }

    public static void save() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of("UTC");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")
                .withZone(zoneId);
        String formattedDateTime = formatter.format(instant);

        deleteOldLogFiles();
        try {
            String logFilePath = Paths.LOG_PATH + formattedDateTime + ".log";
            FileHandler fileHandler = new FileHandler(logFilePath);

            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);

            logger.setLevel(Level.INFO);

            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "error setting up FileHandler:", e.getMessage());
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
                logger.fine("deleted the oldest file: " + oldestFile.getName());
            } else {
                logger.severe("failed to delete the oldest file: " + oldestFile.getName());
            }
        }
    }
}
