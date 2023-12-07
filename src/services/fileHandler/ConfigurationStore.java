package services.fileHandler;

import models.settings.AppConfiguration;
import models.settings.ImagingSessionConfig;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import utils.Paths;

import java.io.*;

public class ConfigurationStore {
    public static AppConfiguration loadAppConfig() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(Paths.CONFIGURATION_PATH));
            JsonNode application = jsonNode.path("application");

            return objectMapper.readValue(application, AppConfiguration.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static ImagingSessionConfig loadImagingSessionTableConfig() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(Paths.CONFIGURATION_PATH));
            JsonNode imagingSession = jsonNode.path("imagingSessionConfiguration");

            return objectMapper.readValue(imagingSession, ImagingSessionConfig.class);
        } catch (IOException e) {
            return null;
        }
    }
}
