package services.fileHandler;

import models.calibrationFrames.CalibrationFrame;
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

    public static List<CalibrationFrame> load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CalibrationFrame[] calibrationLibraries = objectMapper.readValue(new File(Paths.CALIBRATION_LIBRARY_PATH), CalibrationFrame[].class);

            List<CalibrationFrame> calibrationFrameList = new ArrayList<>(Arrays.asList(calibrationLibraries));

            logger.info("loaded List<CalibrationLibrary> successfully");

            return calibrationFrameList;
        } catch (IOException e) {
            logger.severe("couldn't load List<CalibrationLibrary>:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(List<CalibrationFrame> calibrationLibraries, String path) {
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
