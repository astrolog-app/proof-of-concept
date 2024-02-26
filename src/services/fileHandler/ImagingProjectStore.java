package services.fileHandler;

import models.ImagingProject;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import utils.Paths;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ImagingProjectStore {
    private static final Logger logger = AppLogger.getLogger();

    public static List<ImagingProject> load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ImagingProject[] imagingProjects = objectMapper.readValue(new File(Paths.IMAGING_PROJECTS_PATH), ImagingProject[].class);

            List<ImagingProject> imagingProjectsList = new ArrayList<>(Arrays.asList(imagingProjects));

            logger.info("loaded List<ImagingProject> successfully");

            return imagingProjectsList;
        } catch (Exception e) {
            logger.severe("couldn't load ImagingProject's:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(List<ImagingProject> imagingProjects, String path) {
        String configPath = Objects.requireNonNullElse(path, Paths.IMAGING_PROJECTS_PATH);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(configPath), imagingProjects);

            logger.info("saved ImagingProject's successfully");
        } catch (Exception e) {
            logger.severe("couldn't save ImagingProject's:" + "\t" + e.getMessage());
        }
    }
}
