package utils;

import services.AppLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class FileSystem {
    private static final Logger logger = AppLogger.getLogger();

    public static void copyFile(String fileName, String origin, String destination) {
        Path sourceFile = java.nio.file.Paths.get(origin + fileName);
        Path destinationFile = java.nio.file.Paths.get(destination + fileName);

        try {
            // Copy the file
            Files.copy(sourceFile, destinationFile);

            logger.info("File copied successfully!");
        } catch (IOException e) {
            logger.severe("An error occurred while copying the file:" + "\t" + e.getCause());
        }
    }
}
