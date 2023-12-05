package services.fileHandler;

import models.LoggerColumns;
import models.settings.AppConfiguration;
import models.settings.AppTheme;
import models.settings.NavigationBarPlacement;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import utils.Paths;

import java.io.*;
import java.util.*;

public class ConfigurationStore {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void load(AppConfiguration appConfig) {
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(Paths.CONFIGURATION_PATH));

            appConfig.setNavBarPlacement(NavigationBarPlacement.valueOf(jsonNode.path("application").path("navigationBarPlacement").asText()));
            appConfig.setStartInFullscreen(jsonNode.path("application").path("startInFullscreen").asBoolean());
            appConfig.setTheme(AppTheme.valueOf(jsonNode.path("application").path("theme").asText()));
            appConfig.setFolderPath(jsonNode.path("application").path("folderPath").asText());
            appConfig.setEnableRegularBackups(jsonNode.path("application").path("enableRegularBackups").asBoolean());

            JsonNode imagingSession = jsonNode.path("imagingSession");

            JsonNode selectedColumnsNode = imagingSession.path("selectedColumns");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Config File not found:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception:");
            System.out.println(e.getMessage());
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
        obj.put("navigation_bar_placement", appConfig.getNavBarPlacement().toString());
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
