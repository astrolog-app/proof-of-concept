package ui.corecomponents;

import models.AppConfiguration;
import models.AppTheme;
import models.LoggerColumns;
import services.AppActions;
import services.fileHandler.ConfigurationSaver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class SettingsPanel {
    private final AppConfiguration appConfig;
    private File selectedFile;
    private String folderPath;

    private AppTheme momentaryTheme;
    private String momentaryFolderPath;
    private List<LoggerColumns> momentarySelectedColumns;
    private boolean momentaryStartInFullscreen;

    private JPanel mainPanel;
    private JTextField textField1;
    private JButton changeButton;
    private JRadioButton lightRadioButton;
    private JRadioButton darkRadioButton;
    private JButton saveButton;
    private JButton restartButton;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;

    public SettingsPanel(AppConfiguration appConfig) {
        this.appConfig = appConfig;
        momentaryTheme = appConfig.getTheme();
        momentaryFolderPath = appConfig.getFolderPath();
        momentarySelectedColumns = appConfig.getSelectedColumns();
        momentaryStartInFullscreen = appConfig.getStartInFullscreen();

        saveButton.setEnabled(false);
        imagingFolderPathHandler();
        themeHandler();
        fullscreenHandler();

        restartButton.addActionListener(e -> AppActions.restart());

        saveButton.addActionListener(e -> {
            appConfig.setTheme(momentaryTheme);
            appConfig.setFolderPath(folderPath);
            //appConfig.setSelectedColumns();
            appConfig.setStartInFullscreen(momentaryStartInFullscreen);

            ConfigurationSaver configurationSaver = new ConfigurationSaver();
            configurationSaver.saveAppConfig(appConfig);
        });
    }

    private void imagingFolderPathHandler() {
        folderPath = appConfig.getFolderPath();
        selectedFile = new File(folderPath);

        textField1.setText(folderPath);
        textField1.setEditable(false);
        textField1.setEnabled(false);

        JFileChooser chooser = new JFileChooser();

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.showOpenDialog(null);
                selectedFile = chooser.getSelectedFile();
                if (selectedFile != null)
                    textField1.setText(selectedFile.toString());

            }
        });
    }

    private void themeHandler() {
        if (appConfig.getTheme().equals(AppTheme.DARK)) {
            darkRadioButton.setSelected(true);
        } else {
            lightRadioButton.setSelected(true);
        }

        lightRadioButton.addActionListener(e -> {
            lightRadioButton.setSelected(true);
            darkRadioButton.setSelected(false);
            momentaryTheme = AppTheme.LIGHT;
            updateChangeState();
        });
        darkRadioButton.addActionListener(e -> {
            lightRadioButton.setSelected(false);
            darkRadioButton.setSelected(true);
            momentaryTheme = AppTheme.DARK;
            updateChangeState();
        });
    }

    private void fullscreenHandler() {
        yesRadioButton.setSelected(appConfig.getStartInFullscreen());
        noRadioButton.setSelected(!appConfig.getStartInFullscreen());

        yesRadioButton.addActionListener(e -> {
            yesRadioButton.setSelected(true);
            noRadioButton.setSelected(false);
            momentaryStartInFullscreen = true;
            updateChangeState();
        });
        noRadioButton.addActionListener(e -> {
            yesRadioButton.setSelected(false);
            noRadioButton.setSelected(true);
            momentaryStartInFullscreen = false;
            updateChangeState();
        });
    }

    private void updateChangeState() {
        if (momentaryTheme == appConfig.getTheme() && momentaryFolderPath.equals(appConfig.getFolderPath())
                && momentarySelectedColumns == appConfig.getSelectedColumns()
                && momentaryStartInFullscreen == appConfig.getStartInFullscreen()) {
            saveButton.setEnabled(false);
        } else {
            saveButton.setEnabled(true);
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
