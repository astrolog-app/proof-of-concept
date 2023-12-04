package ui.customComponents;

import controllers.ImagingSessionController;
import models.ImagingSession;
import models.equipment.Equipment;
import ui.popUps.ImagingSessionInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogTableScrollPane extends JTable {
    ImagingSessionController imagingSessionController;
    Equipment equipment;
    public LogTableScrollPane(ImagingSessionController imagingSessionController, Equipment equipment) {
        this.imagingSessionController = imagingSessionController;
        this.equipment = equipment;

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
                        imagingSessionController.showImagingSessionDetails();
//                        JOptionPane.showMessageDialog(null, "Double-clicked on row: " +
//                                tableModel.getValueAt(selectedRow, 0));
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popupMenu = createPopupMenu();
                    popupMenu.show((Component) e.getSource(), e.getX(), e.getY());
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

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem1 = new JMenuItem("Add From Existing Folder");
        menuItem1.addActionListener((ActionEvent e) -> imagingSessionController.addImagingSessionAutomatically());
        popupMenu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Add Manually");
        menuItem2.addActionListener((ActionEvent e) -> imagingSessionController.addImagingSessionManually(equipment));
        popupMenu.add(menuItem2);

        JMenuItem menuItem3  = new JMenuItem("Show Details");
        menuItem3.addActionListener((ActionEvent e) -> imagingSessionController.showImagingSessionDetails());
        popupMenu.add(menuItem3);

        JMenuItem menuItem4 = new JMenuItem("Edit");
        menuItem4.addActionListener((ActionEvent e) -> imagingSessionController.editImagingSession());
        popupMenu.add(menuItem4);

        JMenuItem menuItem5 = new JMenuItem("Delete");
        menuItem5.addActionListener((ActionEvent e) -> imagingSessionController.removeImagingSession());
        popupMenu.add(menuItem5);

        return popupMenu;
    }

    public ImagingSession getSelectedImagingSession() {
        return null;
    }
}
