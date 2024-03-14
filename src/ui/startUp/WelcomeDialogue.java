package ui.startUp;

import models.settings.AppConfig;
import models.settings.ImagingSessionConfig;
import models.settings.LoggerColumns;
import models.settings.NavigationBarPlacement;
import services.AppActions;
import services.fileHandler.ConfigurationStore;

import javax.swing.*;
import java.util.HashMap;

public class WelcomeDialogue extends JFrame {
    private final AppConfig appConfig = new AppConfig();
    private final ImagingSessionConfig isConfig = new ImagingSessionConfig();
    private JPanel panel1;
    private JButton nextButton;
    private JButton previousButton;
    private JLabel stepLabel;
    private JPanel stepOne;
    private JPanel stepTwo;
    private JButton button1;
    private JPanel stepThree;
    private JPanel stepFour;
    private JButton launchAstroLogButton;
    private JTextPane termsOfServiceTextPane;
    private JCheckBox iAcceptTheTermsCheckBox;
    int stepCount = 1;

    public WelcomeDialogue() {
        String stepText = "Step " + stepCount + " of 4";
        stepLabel.setText(stepText);

        checkPanelState();
        checkButtonState(stepCount);
        handleActions();
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

        if (this.stepCount == 3 && !iAcceptTheTermsCheckBox.isSelected()) {
            nextButton.setEnabled(false);
        }

        this.stepCount = stepCount;
    }

    private void updateLabel() {
        String stepText = "Step " + stepCount + " of 4";
        stepLabel.setText(stepText);
    }

    private void checkPanelState() {
        stepOne.setVisible(false);
        stepTwo.setVisible(false);
        stepThree.setVisible(false);
        stepFour.setVisible(false);
        switch (stepCount) {
            case 1 -> stepOne.setVisible(true);
            case 2 -> stepTwo.setVisible(true);
            case 3 -> stepThree.setVisible(true);
            case 4 -> stepFour.setVisible(true);
        }
    }

    private void handleActions() {
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
        launchAstroLogButton.addActionListener(e -> {
            createDefaultAppConfig();
            ConfigurationStore.save(appConfig, isConfig, null);
            AppActions.restart();
        });
        iAcceptTheTermsCheckBox.addActionListener(e -> checkButtonState(stepCount));
    }

    private void createDefaultAppConfig() {
        appConfig.setEnableRegularBackups(true);
        appConfig.setNavigationBarPlacement(NavigationBarPlacement.LEFT);
        appConfig.setStartInFullscreen(false);

        isConfig.setColumnSortingType(SortOrder.DESCENDING);
        isConfig.setDefaultSortedColumn(LoggerColumns.DATE);
        isConfig.setSelectedColumns(LoggerColumns.getAllColumns());
        HashMap<LoggerColumns, Integer> map = new HashMap<>();
        for (LoggerColumns l : LoggerColumns.getAllColumns()) {
            map.put(l, 150);
        }
        isConfig.setColumnsSize(map);
    }
}
