package ui.corecomponents;

import models.AppConfiguration;
import models.ApplicationTheme;
import services.ApplicationActions;
import services.fileHandler.FileSaver;

import javax.swing.*;
import java.io.File;

public class SettingsPanel {
    private final AppConfiguration appConfig;
    private File selectedDirectory;
    private boolean settingsChanged = true;
    private String folderPath;

    private JPanel mainPanel;
    private JTextField textField1;
    private JButton changeButton;
    private JRadioButton lightRadioButton;
    private JRadioButton darkRadioButton;
    private JButton saveButton;
    private JButton restartButton;

    public SettingsPanel(AppConfiguration appConfig) {
        this.appConfig = appConfig;

        imagingFolderPathHandler();
        themeHandler();
        saveButton.setEnabled(settingsChanged);

        restartButton.addActionListener(e -> ApplicationActions.restart());

        saveButton.addActionListener(e -> {
            if (darkRadioButton.isSelected()) {
                appConfig.setTheme(ApplicationTheme.DARK);
            } else {
                appConfig.setTheme(ApplicationTheme.LIGHT);
            }
            appConfig.setFolderPath(folderPath);
            //appConfig.setSelectedColumns();

            FileSaver fileSaver = new FileSaver(appConfig);
            fileSaver.saveAppConfig();
        });
    }

    private void imagingFolderPathHandler() {
        folderPath = appConfig.getFolderPath();
        selectedDirectory = new File(folderPath);

        textField1.setText(folderPath);
        textField1.setEditable(false);
        textField1.setEnabled(false);

        changeButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedDirectory = chooser.getCurrentDirectory();
                textField1.setText(selectedDirectory.toString());
            }
        });
    }

    private void themeHandler() {
        if (appConfig.getTheme().equals(ApplicationTheme.DARK)) {
            darkRadioButton.setSelected(true);
        } else {
            lightRadioButton.setSelected(true);
        }

        lightRadioButton.addActionListener(e -> {
            lightRadioButton.setSelected(true);
            darkRadioButton.setSelected(false);
        });
        darkRadioButton.addActionListener(e -> {
            lightRadioButton.setSelected(false);
            darkRadioButton.setSelected(true);
        });
    }

    private void updateChangeState() {
        settingsChanged = !settingsChanged;
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
