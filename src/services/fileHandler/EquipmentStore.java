package services.fileHandler;

import models.LoggerColumns;
import models.equipment.Equipment;
import models.equipment.Telescope;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Paths;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EquipmentStore {
    private Equipment equipment;

    public EquipmentStore(Equipment equipment) {
        this.equipment = equipment;
    }

    public void load() {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(Paths.EQUIPMENT_PATH);

            Object jsonObj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) jsonObj;

            List<Telescope> telescopes = readTelescopes(jsonObject);

            reader.close();

           equipment.addTelescopes(telescopes);
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

//    public void save() {
//        JSONObject obj = new JSONObject();
//
//        obj.put("theme", appConfig.getTheme().toString());
//        obj.put("folder_path", appConfig.getFolderPath());
//        List<String> selectedColumns = new ArrayList<>();
//        for (LoggerColumns lc : appConfig.getSelectedColumns()) {
//            selectedColumns.add(lc.toString());
//        }
//        obj.put("selected_columns", selectedColumns);
//        obj.put("start_in_fullscreen", appConfig.getStartInFullscreen());
//
//        try {
//            FileWriter file = new FileWriter(Paths.CONFIGURATION_PATH);
//            file.write(obj.toJSONString());
//            file.flush();
//            file.close();
//
//        } catch (IOException e) {
//            System.out.println("Error writing Json file.");
//        }
//    }

    private List<Telescope> readTelescopes(JSONObject jsonObject) {
        List<Telescope> telescopes = new ArrayList<>();
        JSONArray telescopesJson = (JSONArray) jsonObject.get("telescopes");
        Iterator<JSONObject> telescopeIterator = telescopesJson.iterator();
        while (telescopeIterator.hasNext()) {
            JSONObject telescopeObj = (JSONObject) telescopeIterator.next();
            String name = (String) telescopeObj.get("name");
            String brand = (String) telescopeObj.get("brand");
            Integer focalLength = (Integer) telescopeObj.get("focal_length");
            Integer aperture = (Integer) telescopeObj.get("aperture");
            Telescope telescope = new Telescope(name, brand, focalLength, aperture);
            telescopes.add(telescope);
        }

        return telescopes;
    }
}
