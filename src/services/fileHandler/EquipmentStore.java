package services.fileHandler;

import models.equipment.*;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class EquipmentStore {
    private static final Logger logger = AppLogger.getLogger();

    public static Equipment load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            EquipmentWrapper equipmentWrapper = objectMapper.readValue(new File(Paths.EQUIPMENT_PATH), EquipmentWrapper.class);

            logger.info("loaded Equipment successfully");

            Equipment equipment = new Equipment();

            // TODO: cleanUp
            Map<UUID, Telescope> telescopeMap = new HashMap<>();
            for (Telescope t : equipmentWrapper.getTelescopes()) {
                telescopeMap.put(t.getId(), t);
            }
            equipment.setTelescopes(telescopeMap);

            Map<UUID, Camera> cameraMap = new HashMap<>();
            for (Camera c : equipmentWrapper.getCameras()) {
                cameraMap.put(c.getId(), c);
            }
            equipment.setCameras(cameraMap);

            Map<UUID, Flattener> flattenerMap = new HashMap<>();
            for (Flattener f : equipmentWrapper.getFlatteners()) {
                flattenerMap.put(f.getId(), f);
            }
            equipment.setFlatteners(flattenerMap);

            Map<UUID, Accessoire> accessoireMap = new HashMap<>();
            for (Accessoire a : equipmentWrapper.getAccessoires()) {
                accessoireMap.put(a.getId(), a);
            }
            equipment.setAccessoires(accessoireMap);

            Map<UUID, Filter> filterMap = new HashMap<>();
            for (Filter f : equipmentWrapper.getFilters()) {
                filterMap.put(f.getId(), f);
            }
            equipment.setFilters(filterMap);

            Map<UUID, Mount> mountMap = new HashMap<>();
            for (Mount m : equipmentWrapper.getMounts()) {
                mountMap.put(m.getId(), m);
            }
            equipment.setMounts(mountMap);

            return equipment;
        } catch (IOException e) {
            logger.severe("couldn't load Equipment:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(Equipment equipment, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.EQUIPMENT_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        // TODO: clean up
        List<List<EquipmentItem>> list  = new ArrayList<>();

        List<EquipmentItem> telescopes = new ArrayList<>(equipment.getTelescopes().values());
        List<EquipmentItem> mounts = new ArrayList<>(equipment.getMounts().values());
        List<EquipmentItem> filters = new ArrayList<>(equipment.getFilters().values());
        List<EquipmentItem> flatteners = new ArrayList<>(equipment.getFlatteners().values());
        List<EquipmentItem> cameras = new ArrayList<>(equipment.getCameras().values());
        List<EquipmentItem> accessoires = new ArrayList<>(equipment.getAccessoires().values());

         list.add(telescopes);
         list.add(mounts);
         list.add(filters);
         list.add(flatteners);
         list.add(cameras);
         list.add(accessoires);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), list);

            logger.info("saved Equipment successfully");
        } catch (Exception e) {
            logger.severe("couldn't save Equipment:" + "\t" + e.getMessage());
        }
    }
}
