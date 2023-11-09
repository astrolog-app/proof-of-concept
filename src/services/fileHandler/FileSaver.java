package services.fileHandler;

import models.AppConfiguration;
import models.LoggerColumns;
import org.json.simple.JSONObject;
import models.Path;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSaver {
    public void saveAppConfig(AppConfiguration appConfig) {
        JSONObject obj = new JSONObject();

        obj.put("theme", appConfig.getTheme().toString());
        obj.put("folder_path", appConfig.getFolderPath());
        List<String> selectedColumns = new ArrayList<>();
        for (LoggerColumns lc : appConfig.getSelectedColumns()) {
            selectedColumns.add(lc.toString());
        }
        obj.put("selected_columns", selectedColumns);

        try {
            FileWriter file = new FileWriter(Path.configurationPath + "/configuration_test.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing Json file.");
        }
    }
}
