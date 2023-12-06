package services.fileHandler;

import models.imagingSessions.ImagingSession;
import org.codehaus.jackson.map.ObjectMapper;
import utils.Paths;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImagingSessionStore {
    public static List<ImagingSession> loadImagingSessions() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ImagingSession[] imagingSessions = objectMapper.readValue(new File(Paths.IMAGING_SESSIONS_PATH), ImagingSession[].class);

            return new ArrayList<>(Arrays.asList(imagingSessions));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
