package services.fileHandler;

import models.license.Licence;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class LicenseStore {
    private static final Logger logger = AppLogger.getLogger();

    public static Licence load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Licence licence = objectMapper.readValue(new File(Paths.LICENCE_PATH), Licence.class);

            logger.info("loaded Licence successfully");

            return licence;
        } catch (IOException e) {
            logger.severe("couldn't load Licence:" + "\t" + e.getMessage());
            return null;
        }
    }

    public static void save(Licence licence) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(Paths.LICENCE_PATH), licence);

            logger.info("saved Licence successfully");
        } catch (Exception e) {
            logger.severe("couldn't save Licence:" + "\t" + e.getMessage());
        }
    }
}
