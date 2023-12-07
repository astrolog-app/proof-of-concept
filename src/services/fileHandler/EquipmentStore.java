package services.fileHandler;

import models.equipment.Equipment;
import org.codehaus.jackson.map.ObjectMapper;
import utils.Paths;

import java.io.*;

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
}
