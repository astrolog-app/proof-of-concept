package ui.popUps;

import models.equipment.*;
import models.tableModels.ImagingSessionTableModel;
import models.imagingSessions.*;
import services.fileHandler.ImagingSessionStore;
import ui.customComponents.CustomComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewImagingSessionManually extends JDialog {
    private final Equipment equipment;
    private final ImagingSessionTableModel isTableModel;
    private final List<ImagingSession> imagingSessions;

    private JPanel mainPanel;
    private JPanel lightPanel;
    private JPanel darkPanel;
    private JPanel biasPanel;
    private JPanel flatPanel;
    private JCheckBox flatCheckBox;
    private JSpinner totalSubsFlat;
    private JTextField dateFlat;
    private JTextField dateLight;
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
    private JTextField imageFolderField;
    private JButton changeImageFolder;
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

    public NewImagingSessionManually(Equipment equipment, ImagingSession session, ImagingSessionTableModel isTableModel, List<ImagingSession> imagingSessions) {
        this.equipment = equipment;
        this.isTableModel = isTableModel;
        this.imagingSessions = imagingSessions;
        imageFolderField.setEnabled(false);

        updateFlatPanelState();
        updateDarkPanelState();
        updateBiasPanelState();

        setSpinnerModels();

        if (session != null) {
            fillOutPanel(session);
        }

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

    private void fillOutPanel(ImagingSession session) {
        imageFolderField.setText(session.getFolderDir());

        subLengthLight.setValue(checkSessionValue(session.getLightFrame().getSubLength()));
        totalSubsLight.setValue(checkSessionValue(session.getLightFrame().getTotalSubs()));
        integratedSubs.setValue(checkSessionValue(session.getLightFrame().getIntegratedSubs()));
//        filter.setSelectedItem();
        gain.setValue(checkSessionValue(session.getLightFrame().getGain()));
        offset.setValue(checkSessionValue(session.getLightFrame().getOffset()));
        chipTemp.setValue(checkSessionValue(session.getLightFrame().getCameraTemp()));
        temp.setValue(checkSessionValue(session.getLightFrame().getOutsideTemp()));
        avgSeeing.setValue(checkSessionValue(session.getLightFrame().getAverageSeeing()));
        avgCloudCover.setValue(checkSessionValue(session.getLightFrame().getAverageCloudCover()));
//        telescope.setSelectedItem();
//        camera.setSelectedItem();
//        flattener.setSelectedItem();
//        mount.setSelectedItem();

//        libraryDark.setSelectedItem();
//        dateDark.setText();
//        totalSubsDark.setValue();
//
//        libraryBias.setSelectedItem();
//        dateBiasLabel.setText();
//        totalSubsBias.setValue();
//
//        subLengthFlat.setValue();
//        dateFlat.setText();
//        totalSubsFlat.setValue();
    }

    private Double checkSessionValue(Double d) {
        if (d == null) {
            return 0.0;
        }

        return d;
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

    private void handleActions(ImagingSession session) {
        saveButton.addActionListener(e -> {
            if (session == null) {
                ImagingSession newSession = createNewSession();

                ImagingSessionStore.save(imagingSessions, null);
                isTableModel.addSession(newSession);

                dispose();
            }
        });
        cancelButton.addActionListener(e -> dispose());

        flatCheckBox.addActionListener(e -> updateFlatPanelState());
        darkCheckBox.addActionListener(e -> updateDarkPanelState());
        biasCheckBox.addActionListener(e -> updateBiasPanelState());
        changeImageFolder.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showDialog(null, "Select Imaging Folder");
        });
    }

    private ImagingSession createNewSession() {
//        TODO: finish

        ImagingSession newSession = new ImagingSession();

        LightFrame lf = new LightFrame();
        lf.setDate(dateLight.getText());
        lf.setTarget(target.getText());
        lf.setSubLength((Double) subLengthLight.getValue());
        lf.setTotalSubs((Double) totalSubsLight.getValue());
        lf.setIntegratedSubs((Double) integratedSubs.getValue());
//        lf.setFilter();
        lf.setGain((Double) gain.getValue());
        lf.setOffset((Double) gain.getValue());
        lf.setCameraTemp((Double) gain.getValue());
        lf.setOutsideTemp((Double) temp.getValue());
        lf.setAverageSeeing((Double) avgSeeing.getValue());
        lf.setAverageCloudCover((Double) avgCloudCover.getValue());
//        lf.setTelescope();
//        lf.setCamera();
//        lf.setFlattener();
//        lf.setMount();

        DarkFrame df = new DarkFrame();

        BiasFrame bf = new BiasFrame();

        FlatFrame ff = new FlatFrame();

        newSession.setLightFrame(lf);
        newSession.setDarkFrame(df);
        newSession.setBiasFrame(bf);
        newSession.setFlatFrame(ff);

        return newSession;
    }

    private void updateDarkPanelState() {
        if (darkCheckBox.isSelected()) {
            dateDark.setEnabled(true);
            totalSubsDark.setEnabled(true);
            libraryDark.setEnabled(true);

            darkFramesLabel.setEnabled(true);
            dateDarkLabel.setEnabled(true);
            totalSubsDarkLabel.setEnabled(true);
            libraryDarkLabel.setEnabled(true);
        } else {
            dateDark.setEnabled(false);
            totalSubsDark.setEnabled(false);
            libraryDark.setEnabled(false);

            darkFramesLabel.setEnabled(false);
            dateDarkLabel.setEnabled(false);
            totalSubsDarkLabel.setEnabled(false);
            libraryDarkLabel.setEnabled(false);
        }
    }

    private void updateBiasPanelState() {
        if (biasCheckBox.isSelected()) {
            dateBias.setEnabled(true);
            totalSubsBias.setEnabled(true);
            libraryBias.setEnabled(true);

            biasFramesLabel.setEnabled(true);
            dateBiasLabel.setEnabled(true);
            totalSubsBiasLabel.setEnabled(true);
            libraryBiasLabel.setEnabled(true);
        } else {
            dateBias.setEnabled(false);
            totalSubsBias.setEnabled(false);
            libraryBias.setEnabled(false);

            biasFramesLabel.setEnabled(false);
            dateBiasLabel.setEnabled(false);
            totalSubsBiasLabel.setEnabled(false);
            libraryBiasLabel.setEnabled(false);
        }
    }

    private void updateFlatPanelState() {
        if (flatCheckBox.isSelected()) {
            dateFlat.setEnabled(true);
            subLengthFlat.setEnabled(true);
            totalSubsFlat.setEnabled(true);
            flatFramesLabel.setEnabled(true);
            dateFlatLabel.setEnabled(true);
            totalSubsFlatLabel.setEnabled(true);
            subLengthFlatLabel.setEnabled(true);
        } else {
            dateFlat.setEnabled(false);
            subLengthFlat.setEnabled(false);
            totalSubsFlat.setEnabled(false);
            flatFramesLabel.setEnabled(false);
            dateFlatLabel.setEnabled(false);
            totalSubsFlatLabel.setEnabled(false);
            subLengthFlatLabel.setEnabled(false);
        }
    }

    private void createUIComponents() {
//        filter = new CustomComboBox(filterNames, EquipmentType.TELESCOPE, equipment);
        telescope = new CustomComboBox(EquipmentType.TELESCOPE, equipment);
        camera = new CustomComboBox(EquipmentType.CAMERA, equipment);
        flattener = new CustomComboBox(EquipmentType.FLATTENER, equipment);
        mount =  new CustomComboBox(EquipmentType.MOUNT, equipment);
    }
}
