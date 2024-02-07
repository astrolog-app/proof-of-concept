package utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileExplorer {
    public static void openFileExplorer(String path) {
        // Check if Desktop is supported
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }

        // Get the Desktop instance
        Desktop desktop = Desktop.getDesktop();

        // Check if the file exists
        File file = new File(path);
        if (!file.exists()) {
            // hardcode YES_NO_OPTION to disable translation
            UIManager.put("OptionPane.yesButtonText", "Yes");
            UIManager.put("OptionPane.noButtonText", "No");

            JOptionPane.showConfirmDialog(null,
                    "The path of your library entry seems invalid.",
                    "Invalid Configured Path",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            // Open the file explorer with the specified file
            desktop.open(file);
        } catch (IOException e) {
            System.out.println("Failed to open file explorer: " + e.getMessage());
        }
    }
}
