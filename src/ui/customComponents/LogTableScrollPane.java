package ui.customComponents;

import ui.popUps.ImagingSessionInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogTableScrollPane extends JTable {
    public LogTableScrollPane() {
        String[][] data = {
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "100", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "200", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
                {"02.12.2023", "NGC 7000", "3", "300", "Ts-Optics", "AD", "dahoasdhuashd"},
        };

        String[] columnNames = { "Date", "Target", "Subs", "Sub Length", "Telescope", "da", "ASdad" };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        setModel(tableModel);
        showHorizontalLines = true;

        this.getColumnModel().getColumn(0).setPreferredWidth(125);
        this.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.getColumnModel().getColumn(3).setPreferredWidth(150);
        this.getColumnModel().getColumn(4).setPreferredWidth(150);
        this.getColumnModel().getColumn(5).setPreferredWidth(150);
        this.getColumnModel().getColumn(6).setPreferredWidth(150);

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
