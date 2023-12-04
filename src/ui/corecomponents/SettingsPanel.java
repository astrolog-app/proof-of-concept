package ui.corecomponents;

import models.settings.AppConfiguration;
import models.settings.AppTheme;
import models.settings.NavigationBarPlacement;
import services.AppActions;
import services.fileHandler.ConfigurationStore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel {
    private final AppConfiguration appConfig;

    private AppTheme momentaryTheme;
    private String momentaryFolderPath;
    private NavigationBarPlacement momentaryNavBarPlacement;
    private boolean momentaryStartInFullscreen;

    private AppTheme originalTheme;
    private NavigationBarPlacement originalNavBarPlacement;
    private boolean originalStartInFullscreen;

    private JPanel mainPanel;
    private JTextField folderPathField;
    private JButton changeFolderPathButton;
    private JRadioButton lightThemeRadioButton;
    private JRadioButton darkThemeRadioButton;
    private JButton saveChangesButton;
    private JButton restartAppButton;
    private JRadioButton startInFullscreenRadioButton;
    private JRadioButton doNotStartInFullscreenRadioButton;
    private JComboBox navigationBarPlacementDropdown;
    private JLabel placeHolder1;
    private JLabel restartMessage;

    public SettingsPanel(AppConfiguration appConfig) {
        this.appConfig = appConfig;
        momentaryTheme = appConfig.getTheme();
        momentaryFolderPath = appConfig.getFolderPath();
        momentaryNavBarPlacement = appConfig.getNavBarPlacement();
        momentaryStartInFullscreen = appConfig.getStartInFullscreen();

        originalTheme = appConfig.getTheme();
        originalNavBarPlacement = appConfig.getNavBarPlacement();
        originalStartInFullscreen = appConfig.getStartInFullscreen();

        setSaveButtonState();
        setRestartButtonState();
        imagingFolderPathHandler();
        themeHandler();
        navigationBarPlacementHandler();
        fullscreenHandler();

        restartAppButton.addActionListener(e -> AppActions.restart());

        saveChangesButton.addActionListener(e -> {
            appConfig.setTheme(momentaryTheme);
            appConfig.setFolderPath(momentaryFolderPath);
            appConfig.setNavBarPlacement(momentaryNavBarPlacement);
            appConfig.setStartInFullscreen(momentaryStartInFullscreen);
            setSaveButtonState();
            setRestartButtonState();

            ConfigurationStore.save(appConfig, null);
        });
    }

    private void imagingFolderPathHandler() {
        String folderPath = appConfig.getFolderPath();

        folderPathField.setText(folderPath);
        folderPathField.setEditable(false);
        folderPathField.setEnabled(false);

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("Select Folder");

        changeFolderPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    momentaryFolderPath = chooser.getSelectedFile().toString();
                    folderPathField.setText(momentaryFolderPath);
                    setSaveButtonState();
                }
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
            setSaveButtonState();
        });
        darkThemeRadioButton.addActionListener(e -> {
            lightThemeRadioButton.setSelected(false);
            darkThemeRadioButton.setSelected(true);
            momentaryTheme = AppTheme.DARK;
            setSaveButtonState();
        });
    }

    private void navigationBarPlacementHandler() {
        NavigationBarPlacement navigationBarPlacement = appConfig.getNavBarPlacement();
        switch (navigationBarPlacement) {
            case LEFT -> navigationBarPlacementDropdown.setSelectedItem("Left");
            case TOP -> navigationBarPlacementDropdown.setSelectedItem("Top");
            case RIGHT -> navigationBarPlacementDropdown.setSelectedItem("Right");
            case BOTTOM -> navigationBarPlacementDropdown.setSelectedItem("Bottom");
        }

        navigationBarPlacementDropdown.addActionListener(e -> {
            String s = (String) navigationBarPlacementDropdown.getSelectedItem();
            switch (s) {
                case "Left" -> momentaryNavBarPlacement = NavigationBarPlacement.LEFT;
                case "Top" -> momentaryNavBarPlacement = NavigationBarPlacement.TOP;
                case "Right" -> momentaryNavBarPlacement = NavigationBarPlacement.RIGHT;
                case "Bottom" -> momentaryNavBarPlacement = NavigationBarPlacement.BOTTOM;
            }
            setSaveButtonState();
        });
    }

    private void fullscreenHandler() {
        startInFullscreenRadioButton.setSelected(appConfig.getStartInFullscreen());
        doNotStartInFullscreenRadioButton.setSelected(!appConfig.getStartInFullscreen());

        startInFullscreenRadioButton.addActionListener(e -> {
            startInFullscreenRadioButton.setSelected(true);
            doNotStartInFullscreenRadioButton.setSelected(false);
            momentaryStartInFullscreen = true;
            setSaveButtonState();
        });
        doNotStartInFullscreenRadioButton.addActionListener(e -> {
            startInFullscreenRadioButton.setSelected(false);
            doNotStartInFullscreenRadioButton.setSelected(true);
            momentaryStartInFullscreen = false;
            setSaveButtonState();
        });
    }

    private void setSaveButtonState() {
        saveChangesButton.setEnabled(momentaryTheme != appConfig.getTheme()
                || !momentaryFolderPath.equals(appConfig.getFolderPath())
                || momentaryNavBarPlacement != (appConfig.getNavBarPlacement())
                || momentaryStartInFullscreen != appConfig.getStartInFullscreen());
    }

    private void setRestartButtonState() {
        if (momentaryTheme == originalTheme
                && momentaryStartInFullscreen == originalStartInFullscreen
                && momentaryNavBarPlacement == originalNavBarPlacement) {
            restartAppButton.setVisible(false);
            restartMessage.setVisible(false);
        } else {
            restartAppButton.setVisible(true);
            restartMessage.setVisible(true);
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
