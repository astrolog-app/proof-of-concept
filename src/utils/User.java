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
}
