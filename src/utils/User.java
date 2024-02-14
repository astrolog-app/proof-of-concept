package utils;

import services.AppLogger;

import java.awt.*;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class User {
    private static final Logger logger = AppLogger.getLogger();

    public static String getComputerName() {
        try {
            // Get the local host
            InetAddress localHost = InetAddress.getLocalHost();

            // Get the host name
            return localHost.getHostName();
        } catch (UnknownHostException e) {
            logger.severe("couldn't access computer name");

            return "error";
        }
    }

    public static void openLink(String link) {
        try {
            URI uri = new URI(link);

            // Check if the Desktop is supported, and if so, open the URL
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(uri);
                } else {
                    logger.severe("desktop does not support the browse action");
                }
            } else {
                logger.severe("desktop is not supported");
            }
        } catch (Exception e) {
            logger.severe("couldn't open browser with specified link");
        }
    }
}
