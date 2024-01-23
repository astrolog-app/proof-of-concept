package services.fileHandler;

import models.calibrationLibrary.CalibrationLibrary;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class CalibrationLibraryStore {
    private static final Logger logger = AppLogger.getLogger();

    public static CalibrationLibrary load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CalibrationLibrary calibrationLibrary = objectMapper.readValue(
                    new File(Paths.CALIBRATION_LIBRARY_PATH), CalibrationLibrary.class
            );

            logger.info("loaded CalibrationLibrary successfully");

            return calibrationLibrary;
        } catch (IOException e) {
            logger.severe("couldn't load CalibrationLibrary:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(CalibrationLibrary calibrationLibrary, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.CALIBRATION_LIBRARY_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), calibrationLibrary);

            logger.info("saved CalibrationLibrary successfully");
        } catch (Exception e) {
            logger.severe("couldn't save CalibrationLibrary:" + "\t" + e.getMessage());
        }
    }
}
