package ui.corecomponents;

import models.AppConfiguration;
import models.ApplicationTheme;
import services.ApplicationActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingsPanel {
    private final AppConfiguration appConfig;
    private File selectedFile;
    private boolean settingsChanged = false;

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
    }

    private void imagingFolderPathHandler() {
        String folderPath = appConfig.getFolderPath();
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
