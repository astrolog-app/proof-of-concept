package ui.popUps;

import models.equipment.Equipment;
import models.equipment.EquipmentItem;
import models.equipment.EquipmentType;
import models.equipment.Telescope;
import services.fileHandler.EquipmentStore;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class EquipmentItemPanel extends JDialog {
    private final EquipmentType equipmentType;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JComboBox<String> brandField;
    private JSpinner focalLengthField;
    private JSpinner apertureField;
    private JLabel focalLengthLabel;
    private JLabel apertureLabel;
    private JCheckBox isUsedCheckBox;
    private JLabel spacer1;
    private JLabel spacer2;
    private JLabel spacer3;
    private JLabel factorLabel;
    private JSpinner factor;
    private JLabel chipSizeLabel;
    private JLabel spacer4;
    private JLabel spacer5;
    private JLabel megapixelLabel;
    private JLabel spacer6;
    private JComboBox chipsize;
    private JSpinner megapixel;
    private JRadioButton colorRadioButton;
    private JRadioButton monochromeRadioButton;

    public EquipmentItemPanel(EquipmentType equipmentType, Equipment equipment, EquipmentItem equipmentItem) {
        this.equipmentType = equipmentType;

        List<String> brandList = new ArrayList<>(equipment.getAllBrands());
        Collections.sort(brandList);

        for (String brand : brandList) {
            brandField.addItem(brand);
        }

        setAllInvisible();
        setSpinnerModels();
        setupUI();
        fillUp(equipmentItem);

        saveButton.setEnabled(true); // TODO: handle save button

        saveButton.addActionListener(e -> {
            int focalLength = (int) focalLengthField.getValue();
            int aperture = (int) apertureField.getValue();
            Telescope telescope = new Telescope(UUID.randomUUID(), true, nameField.getText(), "test", focalLength, aperture);
            equipment.addTelescope(telescope);
            EquipmentStore.save(equipment, null);
            dispose();
        });

        brandField.addActionListener(e -> brandField.removeItem(null));

        cancelButton.addActionListener(e -> this.dispose());

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add New " + equipmentType.getName());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fillUp(EquipmentItem equipmentItem) {
        if (equipmentItem != null) {
            nameField.setText(equipmentItem.getName());
            brandField.setSelectedItem(equipmentItem.getBrand());
            isUsedCheckBox.setSelected(equipmentItem.getUsed());

            switch (equipmentType) {
                // TODO: add switch cases
            }
        }
    }

    private void setAllInvisible() {
        spacer1.setVisible(false);
        focalLengthField.setVisible(false);
        focalLengthLabel.setVisible(false);

        spacer2.setVisible(false);
        apertureField.setVisible(false);
        apertureLabel.setVisible(false);

        spacer3.setVisible(false);
        factor.setVisible(false);
        factorLabel.setVisible(false);

        spacer4.setVisible(false);
        chipsize.setVisible(false);
        chipSizeLabel.setVisible(false);

        spacer5.setVisible(false);
        megapixel.setVisible(false);
        megapixelLabel.setVisible(false);

        spacer6.setVisible(false);
        colorRadioButton.setVisible(false);
        monochromeRadioButton.setVisible(false);
    }

    private void setSpinnerModels() {
        focalLengthField.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        apertureField.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        factor.setModel(new SpinnerNumberModel(1.00, 0.01, 100.00, 0.01));
        megapixel.setModel(new SpinnerNumberModel(0, 0, 1000.00, 0.1));
    }

    private void setupUI() {
        isUsedCheckBox.setSelected(true);
        colorRadioButton.setSelected(true);

        switch (equipmentType) {
            case TELESCOPE -> {
                spacer1.setVisible(true);
                focalLengthField.setVisible(true);
                focalLengthLabel.setVisible(true);

                spacer2.setVisible(true);
                apertureField.setVisible(true);
                apertureLabel.setVisible(true);

                setSize(500, 280);
            }
            case FLATTENER -> {
                spacer3.setVisible(true);
                factor.setVisible(true);
                factorLabel.setVisible(true);

                setSize(500, 230);
            }
            case CAMERA -> {
                spacer4.setVisible(true);
                chipsize.setVisible(true);
                chipSizeLabel.setVisible(true);

                spacer5.setVisible(true);
                megapixel.setVisible(true);
                megapixelLabel.setVisible(true);

                spacer6.setVisible(true);
                colorRadioButton.setVisible(true);
                monochromeRadioButton.setVisible(true);

                setSize(500, 320);
            }
            case ACCESSOIRE, MOUNT -> setSize(500, 180);
        }
    }
}
