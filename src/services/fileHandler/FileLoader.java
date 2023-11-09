package services.fileHandler;

import models.ApplicationTheme;
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

public class FileLoader {
    private ApplicationTheme theme;
    private String folderPath;
    private List<LoggerColumns> selectedColumns = new ArrayList<>();

    public FileLoader() {
        loadAppConfig();
    }

    private void loadAppConfig() {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(Path.configurationPath + "/configuration_test.json");

            Object jsonObj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonObj;

            String themeString = (String) jsonObject.get("theme");
            if (themeString.equals("DARK")) {
                theme = ApplicationTheme.DARK;
            } else {
                theme = ApplicationTheme.LIGHT;
            }

            folderPath = (String) jsonObject.get("folder_path");

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

            reader.close();
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

    public ApplicationTheme getTheme() {
        return theme;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public List<LoggerColumns> getSelectedColumns() {
        return selectedColumns;
    }
}
