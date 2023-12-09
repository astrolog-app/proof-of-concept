package services.fileHandler;

import models.equipment.Equipment;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

public class EquipmentStore {
    private static final Logger logger = AppLogger.getLogger();

    public static Equipment load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Equipment equipment = objectMapper.readValue(new File(Paths.EQUIPMENT_PATH), Equipment.class);

            logger.info("loaded Equipment successfully");

            return equipment;
        } catch (IOException e) {
            logger.severe("couldn't load Equipment:" + "\t" + e.getMessage());
            return null;
        }
    }

    public static void save(Equipment equipment, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.EQUIPMENT_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), equipment);

            logger.info("saved Equipment successfully");
        } catch (Exception e) {
            logger.severe("couldn't save Equipment:" + "\t" + e.getMessage());
        }
    }
}
