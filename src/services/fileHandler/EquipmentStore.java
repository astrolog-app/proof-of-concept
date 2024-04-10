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
        EquipmentWrapper ew = new EquipmentWrapper();

        List<Telescope> telescopes = equipment.getTelescopes().values().stream().toList();
        List<Mount> mounts = equipment.getMounts().values().stream().toList();
        List<Filter> filters = equipment.getFilters().values().stream().toList();
        List<Flattener> flatteners = equipment.getFlatteners().values().stream().toList();
        List<Camera> cameras = equipment.getCameras().values().stream().toList();

        ew.setTelescopes(telescopes);
        ew.setMounts(mounts);
        ew.setFilters(filters);
        ew.setFlatteners(flatteners);
        ew.setCameras(cameras);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), ew);

            logger.info("saved Equipment successfully");
        } catch (Exception e) {
            logger.severe("couldn't save Equipment:" + "\t" + e.getMessage());
        }
    }
}
