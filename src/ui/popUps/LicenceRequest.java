package ui.popUps;

import models.license.Licence;
import models.license.LicenceType;
import services.AppLogger;
import services.fileHandler.LicenceStore;
import utils.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.logging.Logger;

public class LicenceRequest extends JDialog {
    private final JFrame parentFrame;
    private final Licence licence = new Licence();
    private JPanel mainPanel;
    private JComboBox<String> licenceComboBox;
    private JTextField keyTextField;
    private JButton activateButton;
    private JButton closeButton;
    private JLabel keyLabel;

    public LicenceRequest(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        handleActions();
        updateLicenceFieldState();

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setContentPane(mainPanel);
        setTitle("Licence Activation");
        setSize(400, 260);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateButtonState() {
        boolean lite = Objects.equals(licenceComboBox.getSelectedItem(), LicenceType.LITE.getName());
        boolean key = !keyTextField.getText().isEmpty();

        activateButton.setEnabled(lite || key);
    }

    private void handleActions() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        closeButton.addActionListener(e -> System.exit(0));
        activateButton.addActionListener(e -> {
            licence.setLicenceType(LicenceType.getEnum(Objects.requireNonNull(licenceComboBox.getSelectedItem()).toString()));
            if (licence.getLicenceType() == LicenceType.LITE) {
                licence.setLicenceKey(null);
            } else {
                licence.setLicenceKey(keyTextField.getText());
            }
            licence.setComputerName(User.getComputerName());

            LicenceStore.save(licence);
            parentFrame.setTitle("AstroLog " + licence.getLicenceType().getName());
            dispose();
        });
        licenceComboBox.addActionListener(e -> {
            updateLicenceFieldState();
            updateButtonState();
        });
        keyTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });
    }

    private void updateLicenceFieldState() {
        if (licenceComboBox.getSelectedIndex() != 0) {
            keyTextField.setEnabled(true);
            keyLabel.setEnabled(true);
        } else {
            keyTextField.setEnabled(false);
            keyLabel.setEnabled(false);
        }
    }
}
