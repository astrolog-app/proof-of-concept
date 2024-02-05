package ui.popUps;

import models.calibrationLibrary.CalibrationLibrary;
import models.equipment.Equipment;

import javax.swing.*;
import java.awt.*;

public class LibraryRowEditor extends JDialog {
    private final CalibrationLibrary library;
    private final Equipment equipment;
    private JPanel mainPanel;

    public LibraryRowEditor(CalibrationLibrary library, Equipment equipment) {
        this.library = library;
        this.equipment = equipment;

        setModal(true);
        add(mainPanel);
        if (library != null) {
            setTitle("Edit Library Entry");
        } else {
            setTitle("Add New Library Entry");
        }
        setSize(1000, 750);
        setMinimumSize(new Dimension(600, 400));
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fillUpUI() {

    }
}
