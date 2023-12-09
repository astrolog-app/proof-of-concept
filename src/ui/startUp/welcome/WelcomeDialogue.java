package ui.startUp.welcome;

import javax.swing.*;

public class WelcomeDialogue extends JFrame {
    private JPanel panel1;
    private JButton nextButton;
    private JButton previousButton;
    private JLabel stepLabel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPanel stepOne;
    private JPanel stepTwo;
    private JButton button1;
    int stepCount = 1;

    public WelcomeDialogue() {
        comboBox1.addActionListener(e -> checkLicenseFieldState());

        String stepText = "Step " + stepCount + " of 4";
        stepLabel.setText(stepText);
        previousButton.addActionListener(e -> {
            checkButtonState(stepCount - 1);
            updateLabel();
            checkPanelState();
        });
        nextButton.addActionListener(e -> {
            checkButtonState(stepCount + 1);
            updateLabel();
            checkPanelState();
        });

        checkPanelState();
        checkLicenseFieldState();
        checkButtonState(stepCount);

        add(panel1);

        setSize(500, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void checkButtonState(int stepCount) {
        previousButton.setEnabled(stepCount != 1);
        nextButton.setEnabled(stepCount != 4);
        this.stepCount = stepCount;
    }

    private void updateLabel() {
        String stepText = "Step " + stepCount + " of 4";
        stepLabel.setText(stepText);
    }

    private void checkPanelState() {
        setAllPanelInvisible();
        switch (stepCount) {
            case 1 -> stepOne.setVisible(true);
            case 2 -> stepTwo.setVisible(true);
        }
    }

    private void setAllPanelInvisible() {
        stepOne.setVisible(false);
        stepTwo.setVisible(false);
    }

    private void checkLicenseFieldState(){
        textField1.setEnabled(comboBox1.getSelectedIndex() != 0);
    }
}
