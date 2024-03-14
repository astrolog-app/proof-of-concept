package services;

import utils.Paths;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class R2 {
    private static final Logger logger = AppLogger.getLogger();

    public static void downloadLatestReleaseNotes() {
        String fileUrl = "https://releases.astro-log.app/latest/releaseNotes.json";
        String saveDir = Paths.CACHE_PATH;

        try {
            URI apiUrl = new URI(fileUrl);

            // Create HTTP connection
            URL url = apiUrl.toURL();
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            // Extracting filename from the URL
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

            FileOutputStream outputStream = new FileOutputStream(saveDir + fileName);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            logger.info("releaseNotes.json downloaded successfully!");
        } catch (IOException e) {
            logger.warning("error while downloading latest releaseNotes.json:" + "\t" + e.getCause());
        } catch (URISyntaxException e) {
            logger.severe("error while downloading latest releaseNotes.json:" + "\t" + e.getCause());
        }
    }
}
