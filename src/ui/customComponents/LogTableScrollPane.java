package ui.customComponents;

import ui.popUps.ImagingSessionInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogTableScrollPane extends JTable {
    public LogTableScrollPane() {
        Object[][] data = {
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

        Object[] columnNames = { "Date", "Target", "Subs", "Sub Length", "Telescope", "da", "ASdad" };

        createTable(data, columnNames);

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

    private void createTable(Object[][] data, Object[] columnNames) {
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        setRowHeight(45);
        setModel(tableModel);
        showHorizontalLines = true;

        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setPreferredWidth(125);
        }
    }
}
