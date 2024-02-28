package services.fileHandler;

import models.imagingFrames.ImagingFrameList;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class ImagingFrameStore {
    private static final Logger logger = AppLogger.getLogger();

    public static ImagingFrameList load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ImagingFrameList imagingFrameList = objectMapper.readValue(new File(Paths.IMAGING_FRAMES_PATH), ImagingFrameList.class);

            logger.info("loaded ImagingFrameList successfully");

            return imagingFrameList;
        } catch (IOException e) {
            logger.severe("couldn't load ImagingFrameList:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(ImagingFrameList imagingFrameList, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.IMAGING_FRAMES_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), imagingFrameList);

            logger.info("saved ImagingFrameList successfully");
        } catch (Exception e) {
            logger.severe("couldn't save ImagingFrameList:" + "\t" + e.getMessage());
        }
    }
}
