package ui.customComponents;

import ui.popUps.ImagingSessionInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogTableScrollPane extends JTable {
    public LogTableScrollPane() {
        String[][] data = {
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics"}
        };

        String[] columnNames = { "Date", "Target", "Subs", "Sub Length", "Telescope" };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        setModel(tableModel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = getSelectedRow();
                    if (selectedRow != -1) {
                        new ImagingSessionInfo();
//                        JOptionPane.showMessageDialog(null, "Double-clicked on row: " +
//                                tableModel.getValueAt(selectedRow, 0));
                    }
                }
            }
        });
    }
}
