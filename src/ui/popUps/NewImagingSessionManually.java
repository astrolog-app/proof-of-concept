package ui.popUps;

import models.equipment.Equipment;
import models.imagingSessions.ImagingSession;
import services.fileHandler.EquipmentStore;

import javax.swing.*;

public class NewImagingSessionManually extends JDialog {
    private final ImagingSession newSession;

    private JPanel mainPanel;
    private JPanel lightPanel;
    private JPanel darkPanel;
    private JPanel biasPanel;
    private JPanel flatPanel;
    private JCheckBox checkBox1;
    private JSpinner spinner2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JTextField textField5;
    private JTextField textField7;
    private JSpinner spinner6;
    private JSpinner spinner7;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox comboBox1;
    private JSpinner spinner11;
    private JSpinner spinner12;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JSpinner spinner1;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JSpinner spinner13;
    private JComboBox comboBox4;
    private JSpinner spinner5;
    private JSpinner spinner8;
    private JSpinner spinner9;
    private JSpinner spinner10;
    private JTextArea textArea1;
    private JSpinner cloudCoverSpinner;

    public NewImagingSessionManually(Equipment equipment, ImagingSession session) {
        newSession = session;
        cancelButton.addActionListener(e -> this.dispose());
//
//        SpinnerModel model = new SpinnerNumberModel(0,0,100,1);
//        cloudCoverSpinner.setModel(model);

        setModal(true);
        add(mainPanel);
        setTitle("Add New Imaging Session");
        setSize(1000, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public ImagingSession getUpdatedSession() {
        return newSession;
    }
}
