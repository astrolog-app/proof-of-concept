package services.fileHandler;

import models.calibrationLibrary.CalibrationLibrary;
import models.imagingSessions.ImagingSession;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class CalibrationLibraryStore {
    private static final Logger logger = AppLogger.getLogger();

    public static List<CalibrationLibrary> load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CalibrationLibrary[] calibrationLibraries = objectMapper.readValue(new File(Paths.CALIBRATION_LIBRARY_PATH), CalibrationLibrary[].class);

            List<CalibrationLibrary> calibrationLibraryList = new ArrayList<>(Arrays.asList(calibrationLibraries));

            logger.info("loaded List<CalibrationLibrary> successfully");

            return calibrationLibraryList;
        } catch (IOException e) {
            logger.severe("couldn't load List<CalibrationLibrary>:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(List<CalibrationLibrary> calibrationLibraries, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.CALIBRATION_LIBRARY_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), calibrationLibraries);

            logger.info("saved CalibrationLibraries successfully");
        } catch (Exception e) {
            logger.severe("couldn't save CalibrationLibraries:" + "\t" + e.getMessage());
        }
    }
}
