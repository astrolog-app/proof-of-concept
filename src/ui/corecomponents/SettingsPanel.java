package ui.corecomponents;

import models.AppConfiguration;
import models.AppTheme;
import models.LoggerColumns;
import services.AppActions;
import services.fileHandler.ConfigurationStore;

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
    private JTextField folderPathField;
    private JButton changeFolderPathButton;
    private JRadioButton lightThemeRadioButton;
    private JRadioButton darkThemeRadioButton;
    private JButton saveChangesButton;
    private JButton restartAppButton;
    private JRadioButton startInFullscreenRadioButton;
    private JRadioButton doNotStartInFullscreenRadioButton;

    public SettingsPanel(AppConfiguration appConfig, ConfigurationStore configStore) {
        this.appConfig = appConfig;
        momentaryTheme = appConfig.getTheme();
        momentaryFolderPath = appConfig.getFolderPath();
        momentarySelectedColumns = appConfig.getSelectedColumns();
        momentaryStartInFullscreen = appConfig.getStartInFullscreen();

        saveChangesButton.setEnabled(false);
        imagingFolderPathHandler();
        themeHandler();
        fullscreenHandler();

        restartAppButton.addActionListener(e -> AppActions.restart());

        saveChangesButton.addActionListener(e -> {
            appConfig.setTheme(momentaryTheme);
            appConfig.setFolderPath(folderPath);
            //appConfig.setSelectedColumns();
            appConfig.setStartInFullscreen(momentaryStartInFullscreen);
            updateChangeState();

            configStore.save();
        });
    }

    private void imagingFolderPathHandler() {
        folderPath = appConfig.getFolderPath();
        selectedFile = new File(folderPath);

        folderPathField.setText(folderPath);
        folderPathField.setEditable(false);
        folderPathField.setEnabled(false);

        JFileChooser chooser = new JFileChooser();

        changeFolderPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.showOpenDialog(null);
                selectedFile = chooser.getSelectedFile();
                if (selectedFile != null)
                    folderPathField.setText(selectedFile.toString());

            }
        });
    }

    private void themeHandler() {
        if (appConfig.getTheme().equals(AppTheme.DARK)) {
            darkThemeRadioButton.setSelected(true);
        } else {
            lightThemeRadioButton.setSelected(true);
        }

        lightThemeRadioButton.addActionListener(e -> {
            lightThemeRadioButton.setSelected(true);
            darkThemeRadioButton.setSelected(false);
            momentaryTheme = AppTheme.LIGHT;
            updateChangeState();
        });
        darkThemeRadioButton.addActionListener(e -> {
            lightThemeRadioButton.setSelected(false);
            darkThemeRadioButton.setSelected(true);
            momentaryTheme = AppTheme.DARK;
            updateChangeState();
        });
    }

    private void fullscreenHandler() {
        startInFullscreenRadioButton.setSelected(appConfig.getStartInFullscreen());
        doNotStartInFullscreenRadioButton.setSelected(!appConfig.getStartInFullscreen());

        startInFullscreenRadioButton.addActionListener(e -> {
            startInFullscreenRadioButton.setSelected(true);
            doNotStartInFullscreenRadioButton.setSelected(false);
            momentaryStartInFullscreen = true;
            updateChangeState();
        });
        doNotStartInFullscreenRadioButton.addActionListener(e -> {
            startInFullscreenRadioButton.setSelected(false);
            doNotStartInFullscreenRadioButton.setSelected(true);
            momentaryStartInFullscreen = false;
            updateChangeState();
        });
    }

    private void updateChangeState() {
        if (momentaryTheme == appConfig.getTheme() && momentaryFolderPath.equals(appConfig.getFolderPath())
                && momentarySelectedColumns == appConfig.getSelectedColumns()
                && momentaryStartInFullscreen == appConfig.getStartInFullscreen()) {
            saveChangesButton.setEnabled(false);
        } else {
            saveChangesButton.setEnabled(true);
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
