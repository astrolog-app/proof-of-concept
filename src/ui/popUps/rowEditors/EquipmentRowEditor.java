package ui.popUps.rowEditors;

import models.equipment.*;
import services.fileHandler.EquipmentStore;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;
import java.util.List;

public class EquipmentRowEditor extends JDialog {
    private final EquipmentType equipmentType;
    private final Equipment equipment;
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
    private JComboBox<String> chipSize;
    private JSpinner megapixel;
    private JRadioButton colorRadioButton;
    private JRadioButton monochromeRadioButton;
    private JLabel spacer7;
    private JLabel filterTypeLabel;
    private JComboBox<String> filterType;
    private final boolean edit;
    private String prevName = "";
    private String prevBrand = "";
    private boolean prevUsed = true;

    public EquipmentRowEditor(EquipmentType equipmentType, Equipment equipment, EquipmentItem equipmentItem) {
        this.equipmentType = equipmentType;
        this.equipment = equipment;

        edit = equipmentItem != null;

        List<String> brandList = new ArrayList<>(equipment.getAllBrands());
        Collections.sort(brandList);

        for (String brand : brandList) {
            brandField.addItem(brand);
        }

        setAllInvisible();
        setSpinnerModels();
        setComponentsVisible();
        fillUp(equipmentItem);
        handleActions();

        saveButton.setEnabled(false); // TODO: handle save button

        setModal(true);
        setContentPane(mainPanel);
        setTitle("Add New " + equipmentType.getName());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateButtonState() {
        boolean name = !nameField.getText().equals(prevName);
        boolean brand = !Objects.equals(brandField.getSelectedItem(), prevBrand);
        boolean used = isUsedCheckBox.isSelected() != prevUsed;

        if (edit) {
            saveButton.setEnabled(name || brand || used); // TODO: finish
        } else {
            saveButton.setEnabled(name);
        }
    }

    private void fillUp(EquipmentItem equipmentItem) {
        if (edit) {
            nameField.setText(equipmentItem.getName());
            prevName = equipmentItem.getName();
            brandField.setSelectedItem(equipmentItem.getBrand());
            prevBrand = equipmentItem.getBrand();
            isUsedCheckBox.setSelected(equipmentItem.getUsed());
            prevUsed = equipmentItem.getUsed();

            switch (equipmentType) {
                case TELESCOPE -> {
                    focalLengthField.setValue(((Telescope) equipmentItem).getFocalLength());
                    apertureField.setValue(((Telescope) equipmentItem).getAperture());
                }
                case FLATTENER -> factor.setValue(((Flattener) equipmentItem).getFactor());
                case CAMERA -> {
                    chipSize.setSelectedItem(((Camera) equipmentItem).getChipSize());
                    megapixel.setValue(((Camera) equipmentItem).getMegaPixel());
                    colorRadioButton.setSelected(((Camera) equipmentItem).getRgb());
                    monochromeRadioButton.setSelected(!((Camera) equipmentItem).getRgb());
                }
                case FILTER -> filterType.setSelectedItem(((Filter) equipmentItem).getFilterType());
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
        chipSize.setVisible(false);
        chipSizeLabel.setVisible(false);

        spacer5.setVisible(false);
        megapixel.setVisible(false);
        megapixelLabel.setVisible(false);

        spacer6.setVisible(false);
        colorRadioButton.setVisible(false); // TODO: handle radio buttons
        monochromeRadioButton.setVisible(false);

        spacer7.setVisible(false);
        filterType.setVisible(false);
        filterTypeLabel.setVisible(false);
    }

    private void setSpinnerModels() {
        focalLengthField.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        apertureField.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        factor.setModel(new SpinnerNumberModel(1.00, 0.01, 100.00, 0.01));
        megapixel.setModel(new SpinnerNumberModel(0, 0, 1000.00, 0.1));
    }

    private void setComponentsVisible() {
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
                chipSize.setVisible(true);
                chipSizeLabel.setVisible(true);

                spacer5.setVisible(true);
                megapixel.setVisible(true);
                megapixelLabel.setVisible(true);

                spacer6.setVisible(true);
                colorRadioButton.setVisible(true);
                monochromeRadioButton.setVisible(true);

                setSize(500, 320);
            }
            case FILTER -> {
                spacer7.setVisible(true);
                filterType.setVisible(true);
                filterTypeLabel.setVisible(true);

                setSize(500, 230);
            }
            case ACCESSOIRE, MOUNT -> setSize(500, 180);
        }
    }

    private void handleActions() {
        colorRadioButton.addActionListener(e -> {
            colorRadioButton.setSelected(true);
            monochromeRadioButton.setSelected(false);
        });
        monochromeRadioButton.addActionListener(e -> {
            colorRadioButton.setSelected(false);
            monochromeRadioButton.setSelected(true);
        });

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String brand = Objects.requireNonNull(brandField.getSelectedItem()).toString();

            switch (equipmentType) {
                case TELESCOPE -> {
                    int focalLength = (int) focalLengthField.getValue();
                    int aperture = (int) apertureField.getValue();

                    Telescope telescope = new Telescope(UUID.randomUUID(), isUsedCheckBox.isSelected(), name, brand, focalLength, aperture);
                    equipment.addTelescope(telescope);
                }
                case ACCESSOIRE -> {
                    Accessoire accessoire = new Accessoire(UUID.randomUUID(), isUsedCheckBox.isSelected(), name, brand);
                    equipment.addAccessoire(accessoire);
                }
                case MOUNT -> {
                    Mount mount = new Mount(UUID.randomUUID(), isUsedCheckBox.isSelected(), name, brand);
                    equipment.addMount(mount);
                }
                case FILTER -> {
                    String filterType = filterTypeLabel.getText();

                    Filter filter = new Filter(UUID.randomUUID(), isUsedCheckBox.isSelected(), name, brand, filterType);
                    equipment.addFilter(filter);
                }
                case FLATTENER -> {
                    double _factor = (double) factor.getValue();

                    Flattener flattener = new Flattener(UUID.randomUUID(), isUsedCheckBox.isSelected(), name, brand, _factor);
                    equipment.addFlattener(flattener);
                }
                case CAMERA -> {
                    String chipSize = chipSizeLabel.getText();
                    Double megaPixel = (Double) megapixel.getValue();
                    boolean rgb = colorRadioButton.isSelected();

                    Camera camera = new Camera(UUID.randomUUID(), isUsedCheckBox.isSelected(), name, brand, chipSize, megaPixel, rgb);
                    equipment.addCamera(camera);
                }
            }

            EquipmentStore.save(equipment, null);
            dispose();
        });

        brandField.addActionListener(e -> brandField.removeItem(null));

        cancelButton.addActionListener(e -> dispose());

        nameField.getDocument().addDocumentListener(new DocumentListener() {
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
        brandField.addActionListener(e -> updateButtonState());
        isUsedCheckBox.addActionListener(e -> updateButtonState());
    }
}
