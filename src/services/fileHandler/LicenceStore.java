package services.fileHandler;

import models.license.Licence;
import org.codehaus.jackson.map.ObjectMapper;
import services.AppLogger;
import services.licence.LicenceEncryptor;
import utils.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Logger;

public class LicenceStore {
    private static final Logger logger = AppLogger.getLogger();

    public static Licence load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            byte[] encryptedLicenceBytes = Files.readAllBytes(new File(Paths.LICENCE_PATH).toPath());
            String encryptedLicence = new String(encryptedLicenceBytes, StandardCharsets.UTF_8);

            // Decrypt the encrypted licence string
            String decryptedLicence = LicenceEncryptor.decrypt(encryptedLicence);

            // Convert the decrypted JSON string back to Licence object
            return objectMapper.readValue(decryptedLicence, Licence.class);
        } catch (IOException e) {
            logger.warning("couldn't load Licence:" + "\t" + e.getMessage());

            return null;
        }
    }

    public static void save(Licence licence) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String licenceJson = objectMapper.writeValueAsString(licence);

            String encryptedLicence = LicenceEncryptor.encrypt(licenceJson);

            Files.write(java.nio.file.Paths.get(Paths.LICENCE_PATH), encryptedLicence.getBytes());

            logger.info("saved Licence successfully");
        } catch (Exception e) {
            logger.severe("couldn't save Licence:" + "\t" + e.getMessage());
        }
    }
}
