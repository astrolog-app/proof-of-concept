package ui.startUp;

import models.settings.*;
import services.AppActions;
import services.fileHandler.ConfigurationStore;
import ui.customComponents.CustomFileChooser;

import javax.swing.*;
import java.util.HashMap;

public class WelcomeDialogue extends JFrame {
    private final AppConfig appConfig = new AppConfig();
    private final ImagingSessionConfig isConfig = new ImagingSessionConfig();
    private String folderPath = "";
    private JPanel panel1;
    private JButton nextButton;
    private JButton previousButton;
    private JLabel stepLabel;
    private JPanel stepOne;
    private JPanel stepTwo;
    private JPanel stepThree;
    private JPanel stepFour;
    private JButton launchAstroLogButton;
    private JTextPane termsOfServiceTextPane;
    private JCheckBox iAcceptTheTermsCheckBox;
    private JTextArea welcomeTextArea;
    private JPanel fileChooser;
    private JRadioButton fahrenheitRadioButton;
    private JRadioButton celsiusRadioButton;
    private JRadioButton darkRadioButton;
    private JRadioButton lightRadioButton;
    private JCheckBox updatesCheckBox;
    int stepCount = 1;

    public WelcomeDialogue() {
        String stepText = "Step " + stepCount + " of 4";
        stepLabel.setText(stepText);

        checkPanelState();
        checkButtonState(stepCount);
        handleActions();
        add(panel1);

        welcomeTextArea.setEditable(false);
        welcomeTextArea.setFocusable(false);

        setSize(500, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void checkButtonState(int stepCount) {
        previousButton.setEnabled(stepCount != 1);
        nextButton.setEnabled(stepCount != 4);

        boolean theme = lightRadioButton.isSelected() || darkRadioButton.isSelected();
        boolean tempType = celsiusRadioButton.isSelected() || fahrenheitRadioButton.isSelected();
        boolean path = !folderPath.isEmpty();
        boolean passSecondStep = theme && tempType && path;

        if (stepCount == 2 && !passSecondStep) {
            nextButton.setEnabled(false);
        }

        if (stepCount == 3 && !iAcceptTheTermsCheckBox.isSelected()) {
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
            createAppConfig();
            ConfigurationStore.save(appConfig, isConfig, null);
            AppActions.restart();
        });
        celsiusRadioButton.addActionListener(e -> {
            selectCelsiusButton(celsiusRadioButton);
            checkButtonState(stepCount);
        });
        fahrenheitRadioButton.addActionListener(e -> {
            selectCelsiusButton(fahrenheitRadioButton);
            checkButtonState(stepCount);
        });
        lightRadioButton.addActionListener(e -> {
            selectThemeButton(lightRadioButton);
            checkButtonState(stepCount);
        });
        darkRadioButton.addActionListener(e -> {
            selectThemeButton(darkRadioButton);
            checkButtonState(stepCount);
        });
        iAcceptTheTermsCheckBox.addActionListener(e -> {
            checkButtonState(stepCount);
            checkButtonState(stepCount);
        });
    }

    private void selectCelsiusButton(JRadioButton radioButton) {
        celsiusRadioButton.setSelected(false);
        fahrenheitRadioButton.setSelected(false);

        radioButton.setSelected(true);
    }

    private void selectThemeButton(JRadioButton radioButton) {
        lightRadioButton.setSelected(false);
        darkRadioButton.setSelected(false);

        radioButton.setSelected(true);
    }

    private void createAppConfig() {
        appConfig.setEnableRegularBackups(true);
        appConfig.setNavigationBarPlacement(NavigationBarPlacement.LEFT);
        appConfig.setStartInFullscreen(false);
        appConfig.setFolderPath(folderPath);
        TempType tempType;
        if (fahrenheitRadioButton.isSelected()) {
            tempType = TempType.FAHRENHEIT;
        } else {
            tempType = TempType.CELSIUS;
        }
        AppTheme theme;
        if (lightRadioButton.isSelected()) {
            theme = AppTheme.LIGHT;
        } else {
            theme = AppTheme.DARK;
        }
        appConfig.setTheme(theme);
        appConfig.setTempType(tempType);
        appConfig.setEnableRegularBackups(updatesCheckBox.isSelected());

        isConfig.setColumnSortingType(SortOrder.DESCENDING);
        isConfig.setDefaultSortedColumn(LoggerColumns.DATE);
        isConfig.setSelectedColumns(LoggerColumns.getAllColumns());
        HashMap<LoggerColumns, Integer> map = new HashMap<>();
        for (LoggerColumns l : LoggerColumns.getAllColumns()) {
            map.put(l, 150);
        }
        isConfig.setColumnsSize(map);
    }

    private void createUIComponents() {
        AppConfig dummy = new AppConfig();
        dummy.setFolderPath("");
        fileChooser = new CustomFileChooser(dummy, "") {
            @Override
            public void fileChanged() {
                folderPath = ((CustomFileChooser) fileChooser).getPath();
                checkButtonState(stepCount);
            }
        };
    }
}
