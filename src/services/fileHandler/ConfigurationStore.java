package services.fileHandler;

import models.settings.AppConfig;
import models.settings.ImagingSessionConfig;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.node.ObjectNode;
import services.AppLogger;
import utils.Paths;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

public class ConfigurationStore {
    private static final Logger logger = AppLogger.getLogger();

    public static AppConfig loadAppConfig() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(Paths.CONFIGURATION_PATH));
            JsonNode application = jsonNode.path("appConfig");


            AppConfig appConfig = objectMapper.readValue(application, AppConfig.class);

            logger.info("loaded AppConfig successfully");

            return appConfig;
        } catch (IOException e) {
            logger.severe("couldn't load AppConfig:" + "\t" + e.getMessage());
            return null;
        }
    }

    public static ImagingSessionConfig loadImagingSessionConfig() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(Paths.CONFIGURATION_PATH));
            JsonNode imagingSession = jsonNode.path("imagingSessionConfig");

            ImagingSessionConfig imagingSessionConfig = objectMapper.readValue(imagingSession, ImagingSessionConfig.class);

            logger.info("loaded ImagingSessionConfig successfully");

            return imagingSessionConfig;
        } catch (IOException e) {
            logger.severe("couldn't load ImagingSessionConfig:" + "\t" + e.getMessage());
            return null;
        }
    }

    public static void save(AppConfig appConfig, ImagingSessionConfig imagingSessionConfig, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.CONFIGURATION_PATH);

        if (appConfig == null && imagingSessionConfig == null) {
            appConfig = loadAppConfig();
            imagingSessionConfig = loadImagingSessionConfig();
        } else if (appConfig == null) {
            appConfig = loadAppConfig();
        } else if (imagingSessionConfig == null) {
            imagingSessionConfig = loadImagingSessionConfig();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
        JsonNode appConfigNode = objectMapper.valueToTree(appConfig);
        JsonNode imagingSessionConfigNode = objectMapper.valueToTree(imagingSessionConfig);
        ObjectNode parentNode = objectMapper.createObjectNode();
        parentNode.put("appConfig", appConfigNode);
        parentNode.put("imagingSessionConfig", imagingSessionConfigNode);

        try {
            writer.writeValue(new File(configPath), parentNode);
            logger.info("saved config file successfully");
        } catch (IOException e) {
            logger.severe("couldn't save Config" + "\t" + e.getMessage());
        }
    }
}
