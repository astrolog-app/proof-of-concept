package ui.popUps;

import javax.swing.*;

public class NewImagingSessionManually extends JDialog {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JSpinner textField5;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JSpinner spinner5;
    private JComboBox comboBox1;
    private JSpinner spinner6;
    private JSpinner spinner7;
    private JSpinner spinner8;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;

    public NewImagingSessionManually() {
        cancelButton.addActionListener(e -> this.dispose());

        SpinnerModel model = new SpinnerNumberModel(0,0,100,1);
        spinner6.setModel(model);

        setModal(true);
        add(mainPanel);
        setTitle("Add New Imaging Session");
        setSize(750, 900);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
