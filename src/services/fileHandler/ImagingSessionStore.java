package services.fileHandler;

import models.ImagingSession;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ImagingSessionStore {
    private static final Logger logger = AppLogger.getLogger();

    public static List<ImagingSession> loadImagingSessions() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ImagingSession[] imagingSessions = objectMapper.readValue(new File(Paths.IMAGING_SESSIONS_PATH), ImagingSession[].class);

            List<ImagingSession> imagingSessionsList = new ArrayList<>(Arrays.asList(imagingSessions));

            logger.info("loaded List<ImagingSession> successfully");

            return imagingSessionsList;
        } catch (Exception e) {
            logger.severe("couldn't load ImagingSession's:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(List<ImagingSession> imagingSessions, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.IMAGING_SESSIONS_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), imagingSessions);

            logger.info("saved ImagingSession's successfully");
        } catch (Exception e) {
            logger.severe("couldn't save ImagingSession's:" + "\t" + e.getMessage());
        }
    }
}
