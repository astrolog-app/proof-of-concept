package ui.popUps;

import models.license.Licence;
import models.license.LicenceType;
import services.fileHandler.LicenceStore;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

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

            LicenceStore.save(licence);
            parentFrame.setTitle("AstroLog " + licence.getLicenceType().getName());
            dispose();
        });
        licenceComboBox.addActionListener(e -> updateLicenceFieldState());
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
