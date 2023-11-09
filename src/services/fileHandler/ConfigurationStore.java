package services.fileHandler;

import models.AppConfiguration;
import models.AppTheme;
import models.LoggerColumns;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import models.Path;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConfigurationStore {
    private final AppConfiguration appConfig;
    private final List<LoggerColumns> selectedColumns = new ArrayList<>();

    public ConfigurationStore(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public void load() {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(Path.configurationPath);

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

            boolean startInFullscreen = (boolean) jsonObject.get("start_in_fullscreen");

            reader.close();

            appConfig.setTheme(theme);
            appConfig.setFolderPath(folderPath);
            appConfig.setSelectedColumns(selectedColumns);
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

    public void save() {
        JSONObject obj = new JSONObject();

        obj.put("theme", appConfig.getTheme().toString());
        obj.put("folder_path", appConfig.getFolderPath());
        List<String> selectedColumns = new ArrayList<>();
        for (LoggerColumns lc : appConfig.getSelectedColumns()) {
            selectedColumns.add(lc.toString());
        }
        obj.put("selected_columns", selectedColumns);
        obj.put("start_in_fullscreen", appConfig.getStartInFullscreen());

        try {
            FileWriter file = new FileWriter(Path.configurationPath);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing Json file.");
        }
    }
}
