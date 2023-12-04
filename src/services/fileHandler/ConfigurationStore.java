package services.fileHandler;

import models.settings.AppConfiguration;
import models.settings.AppTheme;
import models.LoggerColumns;
import models.settings.NavigationBarPlacement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Paths;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ConfigurationStore {

    public static void load(AppConfiguration appConfig) {
        try {
            List<LoggerColumns> selectedColumns = new ArrayList<>();

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(Paths.CONFIGURATION_PATH);

            Object jsonObj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonObj;

            String themeString = (String) jsonObject.get("theme");
            AppTheme theme;
            if (themeString.equals("DARK")) {
                theme = AppTheme.DARK;
            } else {
                theme = AppTheme.LIGHT;
            }

            String folderPath = (String) jsonObject.get("folder_path");

            JSONArray selectedColumnsJSON = (JSONArray) jsonObject.get("selected_columns");
            @SuppressWarnings("unchecked")
            Iterator<String> it = selectedColumnsJSON.iterator();
            while (it.hasNext()) {
                String columnString = it.next();
                for (LoggerColumns lc : LoggerColumns.values()) {
                    if (lc.toString().equals(columnString)) {
                        selectedColumns.add(lc);
                    }
                }
            }

            NavigationBarPlacement navBarPlacement = null;
            String navBarPlacementString = (String) jsonObject.get("navigation_bar_placement");
            navBarPlacement = switch (navBarPlacementString) {
                case "LEFT" -> NavigationBarPlacement.LEFT;
                case "TOP" -> NavigationBarPlacement.TOP;
                case "RIGHT" -> NavigationBarPlacement.RIGHT;
                case "BOTTOM" -> NavigationBarPlacement.BOTTOM;
                default -> navBarPlacement;
            };

            boolean enableRegularBackups = (boolean) jsonObject.get("enable_regular_backups");

            boolean startInFullscreen = (boolean) jsonObject.get("start_in_fullscreen");

            reader.close();

            appConfig.setTheme(theme);
            appConfig.setFolderPath(folderPath);
            appConfig.setSelectedColumns(selectedColumns);
            appConfig.setNavBarPlacement(navBarPlacement);
            appConfig.setEnableRegularBackups(enableRegularBackups);
            appConfig.setStartInFullscreen(startInFullscreen);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Config File not found:");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception:");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error while parsing JSON:");
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
