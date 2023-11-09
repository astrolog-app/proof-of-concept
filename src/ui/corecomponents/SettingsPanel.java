package ui.corecomponents;

import models.AppConfiguration;
import models.AppTheme;
import services.AppActions;
import services.fileHandler.FileSaver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingsPanel {
    private final AppConfiguration appConfig;
    private File selectedFile;
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

        restartButton.addActionListener(e -> AppActions.restart());

        saveButton.addActionListener(e -> {
            if (darkRadioButton.isSelected()) {
                appConfig.setTheme(AppTheme.DARK);
            } else {
                appConfig.setTheme(AppTheme.LIGHT);
            }
            appConfig.setFolderPath(folderPath);
            //appConfig.setSelectedColumns();

            FileSaver fileSaver = new FileSaver();
            fileSaver.saveAppConfig(appConfig);
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
