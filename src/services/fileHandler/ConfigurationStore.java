package services.fileHandler;

import models.LoggerColumns;
import models.settings.AppConfiguration;
import models.settings.ImagingSessionTableConfig;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import utils.Paths;

import java.io.*;
import java.util.*;

public class ConfigurationStore {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void load(AppConfiguration appConfig) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(Paths.CONFIGURATION_PATH));
            JsonNode application = jsonNode.path("application");
            JsonNode imagingSession = jsonNode.path("imagingSessionTableConfiguration");

            AppConfiguration appConfiguration = objectMapper.readValue(application, AppConfiguration.class);
            ImagingSessionTableConfig imagingSessionTableConfig = objectMapper.readValue(imagingSession, ImagingSessionTableConfig.class);

            System.out.println(appConfiguration.getTheme().toString());
            System.out.println(imagingSessionTableConfig.getSelectedColumns());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(AppConfiguration appConfig, String path) {
        JSONObject obj = new JSONObject();
        String configPath = Objects.requireNonNullElse(path, Paths.CONFIGURATION_PATH);

        obj.put("theme", appConfig.getTheme().toString());
        obj.put("folder_path", appConfig.getFolderPath());
        List<String> selectedColumns = new ArrayList<>();
        for (LoggerColumns lc : appConfig.getSelectedColumns()) {
            selectedColumns.add(lc.toString());
        }
        obj.put("selected_columns", selectedColumns);
        obj.put("navigation_bar_placement", appConfig.getNavigationBarPlacement().toString());
        obj.put("enable_regular_backups", appConfig.getEnableRegularBackups());
        obj.put("start_in_fullscreen", appConfig.getStartInFullscreen());

        try {
            FileWriter file = new FileWriter(configPath);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing Json file.");
        }
    }
}
