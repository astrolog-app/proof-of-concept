package ui.popUps;

import ui.customComponents.CustomComboBox;

import javax.swing.*;

public class NewBrand extends JDialog {
    private final CustomComboBox parent;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField brandField;

    public NewBrand(CustomComboBox parent) {
        this.parent = parent;

        handleActions();

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add New Brand");
        setResizable(false);
        setSize(500, 175);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleActions() {
        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {
            parent.selectNewBrand(brandField.getText());
            dispose();
        });
    }
}
