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
    private JCheckBox checkBox1;
    private JSpinner totalSubsFlat;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField target;
    private JSpinner subLengthLight;
    private JSpinner totalSubsLight;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JTextField textField5;
    private JTextField textField7;
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
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JSpinner avgCloudCover;
    private JComboBox flattener;
    private JSpinner integratedSubs;
    private JSpinner chipTemp;
    private JSpinner offset;
    private JSpinner gain;
    private JTextArea textArea1;
    private JComboBox mount;

    public NewImagingSessionManually(Equipment equipment, ImagingSession session) {
        newSession = session;
        this.equipment = equipment;

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
        setSize(1000, 700);
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
    }

    public ImagingSession getUpdatedSession() {
        return newSession;
    }
}
