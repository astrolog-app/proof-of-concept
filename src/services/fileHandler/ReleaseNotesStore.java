package services.fileHandler;

import models.ReleaseNotes;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class ReleaseNotesStore {
    private static final Logger logger = AppLogger.getLogger();

    public static ReleaseNotes load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode j = objectMapper.readTree(new File(Paths.RELEASE_NOTE_PATH));

            ReleaseNotes releaseNotes = objectMapper.readValue(j, ReleaseNotes.class);

            logger.info("loaded ReleaseNotes successfully");

            return releaseNotes;
        } catch (IOException e) {
            logger.warning("couldn't load ReleaseNotes:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(ReleaseNotes releaseNotes, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.RELEASE_NOTE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), releaseNotes);

            logger.info("saved ReleaseNotes successfully");
        } catch (Exception e) {
            logger.severe("couldn't save ReleaseNotes:" + "\t" + e.getMessage());
        }
    }

    public static void delete() {
        File releaseNotes = new File(Paths.RELEASE_NOTE_PATH);

        if (releaseNotes.delete()) {
            logger.info("deleted ReleaseNotes successfully");
        } else {
            logger.severe("couldn't delete ReleaseNotes");
        }
    }
}
