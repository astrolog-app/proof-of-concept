package utils;

import models.settings.AppConfig;
import models.settings.AppTheme;

import javax.swing.*;
import java.awt.*;

public class Images {
    public static ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static ImageIcon getThemeBasedIcon(AppConfig appConfig, String imageName, int x, int y) {
        ImageIcon icon;
        ImageIcon originalIconLight = new ImageIcon(Paths.IMAGE_PATH + imageName + "_light.png");
        ImageIcon scaledIconLight = Images.scaleImage(originalIconLight, x, y);
        ImageIcon originalIconDark = new ImageIcon(Paths.IMAGE_PATH + imageName + "_dark.png");
        ImageIcon scaledIconDark = Images.scaleImage(originalIconDark, x, y);
        if (appConfig.getTheme().equals(AppTheme.DARK)) {
            icon = scaledIconDark;
        } else {
            icon = scaledIconLight;
        }

        return icon;
    }
}
