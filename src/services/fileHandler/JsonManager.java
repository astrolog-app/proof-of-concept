package services.fileHandler;

import java.nio.file.Files;
import java.nio.file.Path;

public class JsonManager {
    public void saveBackup(Path filePath) {
        try {
            Files.copy(filePath, filePath);
        } catch (Exception e) {

        }
    }
}
