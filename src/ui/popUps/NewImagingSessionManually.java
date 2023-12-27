package ui.popUps;

import models.equipment.*;
import models.imagingSessions.ImagingSession;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewImagingSessionManually extends JDialog {
    private final ImagingSession newSession;
    private final Equipment equipment;

    private JPanel mainPanel;
    private JPanel lightPanel;
    private JPanel darkPanel;
    private JPanel biasPanel;
    private JPanel flatPanel;
    private JCheckBox flatCheckBox;
    private JSpinner totalSubsFlat;
    private JTextField dateFlat;
    private JTextField textField2;
    private JTextField target;
    private JSpinner subLengthLight;
    private JSpinner totalSubsLight;
    private JCheckBox darkCheckBox;
    private JCheckBox biasCheckBox;
    private JTextField dateBias;
    private JTextField dateDark;
    private JSpinner totalSubsDark;
    private JSpinner totalSubsBias;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox filter;
    private JSpinner temp;
    private JSpinner avgSeeing;
    private JComboBox telescope;
    private JComboBox camera;
    private JSpinner subLengthFlat;
    private JComboBox libraryBias;
    private JComboBox libraryDark;
    private JSpinner avgCloudCover;
    private JComboBox flattener;
    private JSpinner integratedSubs;
    private JSpinner chipTemp;
    private JSpinner offset;
    private JSpinner gain;
    private JTextArea textArea1;
    private JComboBox mount;
    private JTextField textField3;
    private JButton changeButton;
    private JLabel flatFramesLabel;
    private JSeparator flatSeparator;
    private JLabel subLengthFlatLabel;
    private JLabel dateFlatLabel;
    private JLabel totalSubsFlatLabel;
    private JLabel darkFramesLabel;
    private JLabel biasFramesLabel;
    private JLabel libraryDarkLabel;
    private JLabel totalSubsDarkLabel;
    private JLabel totalSubsBiasLabel;
    private JLabel dateBiasLabel;
    private JLabel libraryBiasLabel;
    private JLabel dateDarkLabel;

    public NewImagingSessionManually(Equipment equipment, ImagingSession session) {
        newSession = session;
        this.equipment = equipment;

        updateFlatPanelState();
        updateDarkPanelState();
        updateBiasPanelState();

        setSpinnerModels();
        setComboBoxModels();

        handleActions(session);

        setModal(true);
        add(mainPanel);
        if (session != null) {
            setTitle("Edit Imaging Session");
        } else {
            setTitle("Add New Imaging Session");
        }
        setSize(1000, 750);
        setMinimumSize(new Dimension(600, 400));
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setSpinnerModels() {
        avgCloudCover.setModel(new SpinnerNumberModel(0,0,100,1));

        subLengthLight.setModel(new SpinnerNumberModel(0, 0, null, 1));
        totalSubsLight.setModel(new SpinnerNumberModel(0, 0, null, 1));
        integratedSubs.setModel(new SpinnerNumberModel(0, 0, null, 1));
        gain.setModel(new SpinnerNumberModel(0, 0, null, 1));
        offset.setModel(new SpinnerNumberModel(0, 0, null, 1));
        temp.setModel(new SpinnerNumberModel(0, 0, null, 1));
        subLengthFlat.setModel(new SpinnerNumberModel(0, 0, null, 1));
        totalSubsBias.setModel(new SpinnerNumberModel(0, 0, null, 1));
        totalSubsDark.setModel(new SpinnerNumberModel(0, 0, null, 1));
        totalSubsFlat.setModel(new SpinnerNumberModel(0, 0, null, 1));

        chipTemp.setModel(new SpinnerNumberModel(0, 0, 10000, 0.1));
        avgSeeing.setModel(new SpinnerNumberModel(0, 0, 10000, 0.01));
    }

    private void setComboBoxModels() {
        List<String> filterNames = new ArrayList<>();
        for (Filter f : equipment.getFilters()) {
            filterNames.add(f.getName());
        }
        filter.addItem("Add New");
        for (String filter : filterNames) {
            this.filter.addItem(filter);
        }
    }

    private void handleActions(ImagingSession session) {
        saveButton.addActionListener(e -> {});
        cancelButton.addActionListener(e -> dispose());

        flatCheckBox.addActionListener(e -> updateFlatPanelState());
        darkCheckBox.addActionListener(e -> updateDarkPanelState());
        biasCheckBox.addActionListener(e -> updateBiasPanelState());
    }

    private void updateDarkPanelState() {
        if (darkCheckBox.isSelected()) {
            dateDark.setEnabled(true);
            totalSubsDark.setEnabled(true);
            libraryDark.setEnabled(true);

            darkFramesLabel.setForeground(null);
            dateDarkLabel.setForeground(null);
            totalSubsDarkLabel.setForeground(null);
            libraryDarkLabel.setForeground(null);
        } else {
            dateDark.setEnabled(false);
            totalSubsDark.setEnabled(false);
            libraryDark.setEnabled(false);

            darkFramesLabel.setForeground(Color.GRAY);
            dateDarkLabel.setForeground(Color.GRAY);
            totalSubsDarkLabel.setForeground(Color.GRAY);
            libraryDarkLabel.setForeground(Color.GRAY);
        }
    }

    private void updateBiasPanelState() {
        if (biasCheckBox.isSelected()) {
            dateBias.setEnabled(true);
            totalSubsBias.setEnabled(true);
            libraryBias.setEnabled(true);

            biasFramesLabel.setForeground(null);
            dateBiasLabel.setForeground(null);
            totalSubsBiasLabel.setForeground(null);
            libraryBiasLabel.setForeground(null);
        } else {
            dateBias.setEnabled(false);
            totalSubsBias.setEnabled(false);
            libraryBias.setEnabled(false);

            biasFramesLabel.setForeground(Color.GRAY);
            dateBiasLabel.setForeground(Color.GRAY);
            totalSubsBiasLabel.setForeground(Color.GRAY);
            libraryBiasLabel.setForeground(Color.GRAY);
        }
    }

    private void updateFlatPanelState() {
        if (flatCheckBox.isSelected()) {
            dateFlat.setEnabled(true);
            subLengthFlat.setEnabled(true);
            totalSubsFlat.setEnabled(true);
            flatFramesLabel.setForeground(null);
            dateFlatLabel.setForeground(null);
            totalSubsFlatLabel.setForeground(null);
            subLengthFlatLabel.setForeground(null);
        } else {
            dateFlat.setEnabled(false);
            subLengthFlat.setEnabled(false);
            totalSubsFlat.setEnabled(false);
            flatFramesLabel.setForeground(Color.GRAY);
            dateFlatLabel.setForeground(Color.GRAY);
            totalSubsFlatLabel.setForeground(Color.GRAY);
            subLengthFlatLabel.setForeground(Color.GRAY);
        }
    }

    public ImagingSession getUpdatedSession() {
        return newSession;
    }
}
