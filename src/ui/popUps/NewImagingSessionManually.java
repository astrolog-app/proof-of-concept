package ui.popUps;

import models.equipment.Equipment;
import services.fileHandler.EquipmentStore;

import javax.swing.*;

public class NewImagingSessionManually extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField dateTextField;
    private JTextField targetTextField;
    private JSpinner chipTempSpinner;
    private JSpinner gainSpinner;
    private JSpinner integratedSubsSpinner;
    private JSpinner subLengthSpinner;
    private JSpinner offsetSpinner;
    private JSpinner totalSubsSpinner;
    private JComboBox filterComboBox;
    private JSpinner cloudCoverSpinner;
    private JSpinner averageSeeingSpinner;
    private JSpinner tempSpinner;
    private JComboBox flatennerComboBox;
    private JComboBox cameraComboBox;
    private JComboBox telescopeComboBox;
    private JTextField notesTextField;

    public NewImagingSessionManually(Equipment equipment, EquipmentStore equipmentStore) {
        generalHandler();

        cancelButton.addActionListener(e -> this.dispose());
        saveHandler();

        SpinnerModel model = new SpinnerNumberModel(0,0,100,1);
        cloudCoverSpinner.setModel(model);

        setModal(true);
        add(mainPanel);
        setTitle("Add New Imaging Session");
        setSize(800, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generalHandler() {

    }

    private void saveHandler() {
        saveButton.addActionListener(e -> {});
    }
}
