package ui.corecomponents;

import services.fileHandler.ConfigurationStore;
import utils.Paths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

public class LogPanel {
    private final ConfigurationStore configStore;
    private JPanel panel1;
    private JLabel placeHolder1;
    private JButton saveBackupConfigButton;

    public LogPanel(ConfigurationStore configStore) {
        this.configStore = configStore;
        backUpHandler();
    }

    private void backUpHandler() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("Select Folder");
        saveBackupConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    configStore.save(chooser.getSelectedFile().toString()
                            + File.separator
                            + "configuration.json");
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}
