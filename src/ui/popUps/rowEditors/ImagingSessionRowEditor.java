package ui.popUps.rowEditors;

import models.ImagingSession;
import models.imagingFrames.*;
import models.equipment.*;
import models.settings.AppConfig;
import models.tableModels.ImagingSessionTableModel;
import services.fileHandler.ImagingSessionStore;
import ui.customComponents.CustomComboBox;
import ui.customComponents.CustomFileChooser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.UUID;

public class ImagingSessionRowEditor extends JDialog {
    private final Equipment equipment;
    private final ImagingSessionTableModel isTableModel;
    private final List<ImagingSession> imagingSessions;
    private final AppConfig appConfig;
    private final ImagingFrameList imagingFrameList;
    private final boolean edit;

    private JPanel mainPanel;
    private JCheckBox flatCheckBox;
    private JSpinner totalSubsFlat;
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
    private JComboBox<String> telescope;
    private JComboBox<String> camera;
    private JSpinner subLengthFlat;
    private CustomComboBox libraryBias;
    private CustomComboBox libraryDark;
    private JSpinner avgCloudCover;
    private JComboBox<String> flattener;
    private JSpinner integratedSubs;
    private JSpinner chipTemp;
    private JSpinner offset;
    private JSpinner gain;
    private JTextArea textArea1;
    private JComboBox<String> mount;
    private JLabel flatFramesLabel;
    private JSeparator flatSeparator;
    private JLabel subLengthFlatLabel;
    private JLabel totalSubsFlatLabel;
    private JLabel darkFramesLabel;
    private JLabel biasFramesLabel;
    private JLabel libraryDarkLabel;
    private JLabel totalSubsDarkLabel;
    private JLabel totalSubsBiasLabel;
    private JLabel dateBiasLabel;
    private JLabel libraryBiasLabel;
    private JLabel dateDarkLabel;
    private JComboBox<String> filter;
    private JPanel fileChooser;
    private String prevFile = "";
    private String prevTarget = "";

    public ImagingSessionRowEditor(Equipment equipment, ImagingSession session, ImagingSessionTableModel isTableModel,
                                   List<ImagingSession> imagingSessions, AppConfig appConfig, ImagingFrameList imagingFrameList) {
        this.equipment = equipment;
        this.isTableModel = isTableModel;
        this.imagingSessions = imagingSessions;
        this.appConfig = appConfig;
        this.imagingFrameList = imagingFrameList;
        edit = session != null;
        saveButton.setEnabled(false);

        updateFlatPanelState();
        updateDarkPanelState();
        updateBiasPanelState();

        setSpinnerModels();
        handleActions(session);
        fillOutPanel(session);

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
        if (edit) {
            ((CustomFileChooser) fileChooser).setPath(session.getFolderDir());

            dateLight.setText(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getDate());
            target.setText(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getTarget());
            subLengthLight.setValue(checkSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getSubLength()));
            totalSubsLight.setValue(checkIntegerSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getTotalSubs()));
            integratedSubs.setValue(checkIntegerSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getIntegratedSubs()));
//        filter.setSelectedItem();
            gain.setValue(checkIntegerSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getGain()));
            offset.setValue(checkSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getOffset()));
            chipTemp.setValue(checkSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getCameraTemp()));
            temp.setValue(checkSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getOutsideTemp()));
            avgSeeing.setValue(checkSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getAverageSeeing()));
            avgCloudCover.setValue(checkSessionValue(session.getLightFrame(imagingFrameList, session.getLightFrameId()).getAverageCloudCover()));
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
//        totalSubsFlat.setValue();
        }
    }

    private Double checkSessionValue(Double d) {
        if (d == null) {
            return 0.0;
        }

        return d;
    }

    private Integer checkIntegerSessionValue(Integer d) {
        if (d == null) {
            return 0;
        }

        return d;
    }

    private void setSpinnerModels() {
        avgCloudCover.setModel(new SpinnerNumberModel(0,0,100,1));

        subLengthLight.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        totalSubsLight.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        integratedSubs.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        gain.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        offset.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        temp.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        subLengthFlat.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        totalSubsBias.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        totalSubsDark.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        totalSubsFlat.setModel(new SpinnerNumberModel(0, 0, 10000, 1));

        chipTemp.setModel(new SpinnerNumberModel(0, -10000, 10000, 0.1));
        avgSeeing.setModel(new SpinnerNumberModel(0, 0, 10000, 0.01));
    }

    private void handleActions(ImagingSession session) {
        saveButton.addActionListener(e -> {
            if (session == null) {
                ImagingSession newSession = createNewSession();
                imagingSessions.add(newSession);

                ImagingSessionStore.save(imagingSessions, null);
                isTableModel.fireTableDataChanged();

                dispose();
            }
        });
        cancelButton.addActionListener(e -> dispose());

        flatCheckBox.addActionListener(e -> updateFlatPanelState());
        darkCheckBox.addActionListener(e -> updateDarkPanelState());
        biasCheckBox.addActionListener(e -> updateBiasPanelState());

        dateLight.getDocument().addDocumentListener(new DocumentListener() {
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
        target.getDocument().addDocumentListener(new DocumentListener() {
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
        camera.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateButtonState();
            }
        });
    }

    private ImagingSession createNewSession() {
//        TODO: finish

        ImagingSession newSession = new ImagingSession();

        LightFrame lf = new LightFrame();
        lf.setDate(dateLight.getText());
        lf.setTarget(target.getText());
        //lf.setSubLength((Double) subLengthLight.getValue());
        //lf.setTotalSubs((Double) totalSubsLight.getValue());
        //lf.setIntegratedSubs((Double) integratedSubs.getValue());
//        lf.setFilter();
        //lf.setGain((Double) gain.getValue());
        //lf.setOffset((Double) gain.getValue());
        //lf.setCameraTemp((Double) gain.getValue());
        //lf.setOutsideTemp((Double) temp.getValue());
        //lf.setAverageSeeing((Double) avgSeeing.getValue());
        //lf.setAverageCloudCover((Double) avgCloudCover.getValue());
//        lf.setTelescope();
//        lf.setCamera();
//        lf.setFlattener();
//        lf.setMount();

        DarkFrame df = null;
        UUID dfId = null;
        if (darkCheckBox.isSelected()) {
            df = new DarkFrame();
            dfId = df.getId();
        }

        BiasFrame bf = null;
        UUID bfId = null;
        if (biasCheckBox.isSelected()) {
            bf = new BiasFrame();
            bfId = bf.getId();
        }

        FlatFrame ff = null;
        if (flatCheckBox.isSelected()) {
            ff = new FlatFrame();
        }

        /*
            introduces a bug when adding two imaging sessions:
            calibrationFrames.add(df);
            calibrationFrames.add(bf);
         */


        //newSession.setLightFrame(lf);
        //newSession.setFlatFrame(ff);
        newSession.setDarkFrameId(dfId);
        newSession.setBiasFrameId(bfId);

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
            subLengthFlat.setEnabled(true);
            totalSubsFlat.setEnabled(true);
            flatFramesLabel.setEnabled(true);
            totalSubsFlatLabel.setEnabled(true);
            subLengthFlatLabel.setEnabled(true);
        } else {
            subLengthFlat.setEnabled(false);
            totalSubsFlat.setEnabled(false);
            flatFramesLabel.setEnabled(false);
            totalSubsFlatLabel.setEnabled(false);
            subLengthFlatLabel.setEnabled(false);
        }
    }

    private void updateButtonState() {
        boolean file = !prevFile.equals(((CustomFileChooser) fileChooser).getPath());
        boolean target = !prevTarget.equals(this.target.getText());

        saveButton.setEnabled(file && target);
    }

    private void createUIComponents() {
        fileChooser = new CustomFileChooser(appConfig, "Choose Folder:") {
            @Override
            public void fileChanged() {
                updateButtonState();
            }
        };

        filter = new CustomComboBox(EquipmentType.FILTER, equipment);
        telescope = new CustomComboBox(EquipmentType.TELESCOPE, equipment);
        camera = new CustomComboBox(EquipmentType.CAMERA, equipment);
        flattener = new CustomComboBox(EquipmentType.FLATTENER, equipment);
        mount =  new CustomComboBox(EquipmentType.MOUNT, equipment);
        libraryDark = new CustomComboBox(CalibrationType.DARK, equipment, appConfig, imagingFrameList);
        libraryBias = new CustomComboBox(CalibrationType.BIAS, equipment, appConfig, imagingFrameList);
    }
}
