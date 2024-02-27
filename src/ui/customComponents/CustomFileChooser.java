package ui.customComponents;

import models.settings.AppConfig;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class CustomFileChooser extends JPanel {
    private final AppConfig appConfig;
    private final JTextField pathField = new JTextField();
    private final JButton changeButton = new JButton("Change");

    public CustomFileChooser(AppConfig appConfig, String label) {
        this.appConfig = appConfig;

        setLayout(new BorderLayout(10, 0));
        pathField.setEnabled(false);

        add(new JLabel(label), BorderLayout.WEST);
        add(pathField, BorderLayout.CENTER);
        add(changeButton, BorderLayout.EAST);

        handleActions();
    }

    private void handleActions() {
        changeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Folder");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showDialog(null, "Select");
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                pathField.setText(selectedFolder.getAbsolutePath().replace(appConfig.getFolderPath(),""));
                fileChanged();
            }
        });
    }

    public String getPath() {
        return pathField.getText();
    }
    public void setPath(String path) {
        pathField.setText(path);
    }

    public abstract void fileChanged();
}
