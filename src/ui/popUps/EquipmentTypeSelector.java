package ui.popUps;

import models.EquipmentListModel;
import models.equipment.Equipment;
import models.equipment.EquipmentType;
import ui.customComponents.equipmentPanelContent.EquipmentPanelContentWrapper;
import ui.popUps.rowEditors.EquipmentRowEditor;

import javax.swing.*;
import java.util.Objects;

public class EquipmentTypeSelector extends JDialog {
    private final Equipment equipment;
    private final EquipmentListModel model;
    private final EquipmentPanelContentWrapper wrapper;
    private JPanel mainPanel;
    private JButton continueButton;
    private JButton cancelButton;
    private JRadioButton telescopeRadioButton;
    private JRadioButton cameraRadioButton;
    private JRadioButton mountRadioButton;
    private JRadioButton filterRadioButton;
    private JRadioButton flattenerRadioButton;
    private JRadioButton accessoireRadioButton;
    private JRadioButton selectedRadioButton = telescopeRadioButton;

    public EquipmentTypeSelector(Equipment equipment, EquipmentListModel model, EquipmentPanelContentWrapper wrapper) {
        this.equipment = equipment;
        this.model = model;
        this.wrapper = wrapper;

        telescopeRadioButton.setSelected(true);
        handleActions();

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Select Equipment Type");
        setResizable(false);
        setSize(350, 275);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleActions() {
        cancelButton.addActionListener(e -> dispose());
        continueButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() ->
                    new EquipmentRowEditor(Objects.requireNonNull(EquipmentType.getEnum(selectedRadioButton.getText())),
                            equipment, null, model, wrapper)
                );
                dispose();
            }
        );

        cameraRadioButton.addActionListener(e -> selectRadioButton(cameraRadioButton));
        telescopeRadioButton.addActionListener(e -> selectRadioButton(telescopeRadioButton));
        mountRadioButton.addActionListener(e -> selectRadioButton(mountRadioButton));
        filterRadioButton.addActionListener(e -> selectRadioButton(filterRadioButton));
        flattenerRadioButton.addActionListener(e -> selectRadioButton(flattenerRadioButton));
        accessoireRadioButton.addActionListener(e -> selectRadioButton(accessoireRadioButton));
    }

    private void selectRadioButton(JRadioButton radioButton) {
        cameraRadioButton.setSelected(false);
        telescopeRadioButton.setSelected(false);
        mountRadioButton.setSelected(false);
        filterRadioButton.setSelected(false);
        flattenerRadioButton.setSelected(false);
        accessoireRadioButton.setSelected(false);

        selectedRadioButton = radioButton;
        radioButton.setSelected(true);
    }
}
