package ui.startUp.welcome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeDialogue extends JFrame {
    private JPanel panel1;
    private JButton nextButton;
    private JButton previousButton;
    private JLabel stepLabel;
    private WelcomeHandler welcomeHandler1;
    int stepCount = 1;

    public WelcomeDialogue() {
        String stepText = "Step " + stepCount + " of 4";
        stepLabel.setText(stepText);
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButtonState(stepCount + 1);
                updateLabel();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButtonState(stepCount + 1);
                updateLabel();
            }
        });

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
    }

    private void updateLabel() {
        String stepText = "Step " + stepCount + " of 4";
        System.out.println(stepCount);
        stepLabel.setText(stepText);
    }

    private void createUIComponents() {
        welcomeHandler1 = new WelcomeHandler();
    }
}
