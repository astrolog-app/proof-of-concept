package services.fileHandler;

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

    public void save() {
        JSONObject obj = new JSONObject();

        JSONArray telescopes = new JSONArray();
        for (Telescope telescope : equipment.getTelescopes()) {
            JSONObject telescopeComponent = new JSONObject();
            telescopeComponent.put("name", telescope.getName());
            telescopeComponent.put("brand", telescope.getBrand());
            telescopeComponent.put("focal_length", telescope.getFocalLength());
            telescopeComponent.put("aperture", telescope.getAperture());
            telescopes.add(telescopeComponent);
        }

        obj.put("telescopes", telescopes);

        try {
            FileWriter file = new FileWriter(Paths.EQUIPMENT_PATH);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing Json file.");
        }
    }

    private List<Telescope> readTelescopes(JSONObject jsonObject) {
        List<Telescope> telescopes = new ArrayList<>();
        JSONArray telescopesJson = (JSONArray) jsonObject.get("telescopes");
        Iterator<JSONObject> telescopeIterator = telescopesJson.iterator();
        while (telescopeIterator.hasNext()) {
            JSONObject telescopeObj = (JSONObject) telescopeIterator.next();
            String name = (String) telescopeObj.get("name");
            String brand = (String) telescopeObj.get("brand");
            Long focalLength = (Long) telescopeObj.get("focal_length");
            Long aperture = (Long) telescopeObj.get("aperture");
            Telescope telescope = new Telescope(name, brand, focalLength.intValue(), aperture.intValue());
            telescopes.add(telescope);
        }

        return telescopes;
    }
}
