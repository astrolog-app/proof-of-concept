package ui.popUps;

import models.license.Licence;
import models.license.LicenceType;
import services.fileHandler.LicenceStore;
import utils.Enums;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LicenceRequest extends JDialog {
    private final JFrame parentFrame;
    private Licence licence = new Licence();
    private JPanel mainPanel;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton saveButton;
    private JButton closeButton;

    public LicenceRequest(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        handleActions();

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add Licence");
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleActions() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        closeButton.addActionListener(e -> System.exit(0));
        saveButton.addActionListener(e -> {
            licence.setLicenceType(LicenceType.LITE);
            licence.setLicenceKey("test");
            LicenceStore.save(licence);
            parentFrame.setTitle("AstroLog " + Enums.enumToString(licence.getLicenceType()));
            dispose();
        });
    }
}
