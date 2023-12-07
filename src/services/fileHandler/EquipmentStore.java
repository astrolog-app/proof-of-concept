package services.fileHandler;

import models.equipment.Equipment;
import models.equipment.Telescope;
import models.settings.AppConfiguration;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Paths;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class EquipmentStore {

    public static Equipment load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(Paths.EQUIPMENT_PATH), Equipment.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save(Equipment equipment, String path) {
        JSONObject obj = new JSONObject();
        String configPath = Objects.requireNonNullElse(path, Paths.CONFIGURATION_PATH);

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
            FileWriter file = new FileWriter(configPath);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing Json file.");
        }
    }
}
