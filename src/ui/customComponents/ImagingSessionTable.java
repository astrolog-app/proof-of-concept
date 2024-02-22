package ui.customComponents;

import controllers.ImagingSessionController;
import models.calibrationFrames.CalibrationFrame;
import models.settings.AppConfig;
import models.tableModels.ImagingSessionTableModel;
import models.imagingSessions.ImagingSession;
import models.settings.LoggerColumns;
import models.equipment.Equipment;
import models.settings.ImagingSessionConfig;
import ui.corecomponents.LogPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;

public class ImagingSessionTable extends JTable {
    private final ImagingSessionConfig isConfig;
    private ImagingSessionController imagingSessionController;
    private final Equipment equipment;
    private final LogPanel logPanel;
    private final AppConfig appConfig;
    private final List<CalibrationFrame> calibrationFrame;
    private final ImagingSessionTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private final List<ImagingSession> imagingSessions;
    private LoggerColumns sortedColumn;
    private SortOrder sortingDirection;

    public ImagingSessionTable(Equipment equipment, LogPanel logPanel,
                               List<ImagingSession> imagingSessions, ImagingSessionConfig isConfig, AppConfig appConfig, List<CalibrationFrame> calibrationFrame) {
        this.logPanel = logPanel;
        this.equipment = equipment;
        this.tableModel = new ImagingSessionTableModel(imagingSessions, equipment, isConfig);
        this.imagingSessions = imagingSessions;
        this.isConfig = isConfig;
        this.appConfig = appConfig;
        this.calibrationFrame = calibrationFrame;

        createTable();
        setColumnsWidth();
        sortRows();
        handleActions();
    }

    private void createTable() {
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(tableModel);
        setDefaultRenderer(Object.class, new CustomRowRenderer());

        setRowHeight(45);
        showHorizontalLines = true;

        setAutoCreateRowSorter(true);
    }

    private void setColumnsWidth() {
        for (LoggerColumns lc : tableModel.getSelectedColumns()) {
            Integer width = isConfig.getColumnsSize().get(lc);

            if (width == null) {
                width = 150;
            }

            getColumnModel().getColumn(tableModel.getColumnAt(lc)).setPreferredWidth(width);
        }
    }

    private void sortRows() {
        if (isConfig != null) {
            sortedColumn = isConfig.getDefaultSortedColumn();
            sortingDirection = isConfig.getColumnSortingType();
        } else  {
            sortedColumn = LoggerColumns.DATE;
            sortingDirection = SortOrder.DESCENDING;
        }
        int defaultSortedColumnInt = tableModel.getColumnAt(sortedColumn);

        sorter = (TableRowSorter<TableModel>) getRowSorter();
//        updateSortComparators();
        sorter.setSortKeys(List.of(new RowSorter.SortKey(defaultSortedColumnInt, sortingDirection)));
        setRowSorter(sorter);
    }

    private void updateSortComparators() {
        int column = 0;

        for (LoggerColumns lc : tableModel.getSelectedColumns()) {
            switch (lc) {
                case SUB_LENGTH,
                        TOTAL_EXPOSURE,
                        INTEGRATED_SUBS,
                        INTEGRATED_EXPOSURE,
                        GAIN,
                        OFFSET,
                        CAMERA_TEMP,
                        OUTSIDE_TEMP,
                        AVERAGE_SEEING,
                        AVERAGE_CLOUD_COVER,
                        AVERAGE_MOON-> sorter.setComparator(column, Comparator.comparing(o -> Double.parseDouble(o.toString())));
            }

            column++;
        }
        setRowSorter(sorter);
    }

    private void handleActions() {
        getSelectionModel().addListSelectionListener(e -> logPanel.updateTableButtonState());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popupMenu = createPopupMenu();
                    popupMenu.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }
        });

        getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = columnAtPoint(e.getPoint());
                System.out.println("Column " + columnIndex + " clicked");
                updateSortingInfo();
                System.out.println(sortedColumn);
                System.out.println(sortingDirection);
                tableModel.updateDataSorting(sortedColumn, sortingDirection);
            }
        });
    }

    private void updateSortingInfo() {
        sortedColumn = tableModel.getSelectedColumns().get(sorter.getSortKeys().get(0).getColumn());
        sortingDirection = sorter.getSortKeys().get(0).getSortOrder();
    }

    private JPopupMenu createPopupMenu() {
        boolean enableAll = tableModel.getSession(getSelectedRow()) != null;
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem1 = new JMenuItem("Add From Existing Folder");
        menuItem1.addActionListener((ActionEvent e) -> imagingSessionController.addImagingSessionAutomatically());
        popupMenu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Add Manually");
        menuItem2.addActionListener((ActionEvent e) -> imagingSessionController.addImagingSessionManually(equipment, imagingSessions, appConfig, calibrationFrame));
        popupMenu.add(menuItem2);

        JMenuItem menuItem4 = new JMenuItem("Edit");
        menuItem4.addActionListener((ActionEvent e) -> imagingSessionController.editImagingSession(equipment, tableModel.getSession(getSelectedRow()), imagingSessions, appConfig, calibrationFrame));
        menuItem4.setEnabled(enableAll);
        popupMenu.add(menuItem4);

        JMenuItem menuItem5 = new JMenuItem("Delete");
        menuItem5.addActionListener((ActionEvent e) -> imagingSessionController.removeImagingSession());
        menuItem5.setEnabled(enableAll);
        popupMenu.add(menuItem5);

        return popupMenu;
    }

    private static class CustomRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                // Set the background color for selected rows
                component.setBackground(table.getSelectionBackground());
            } else {
                Color grey = new Color(110, 110, 110, 110);

                // TODO: group ImagingSession's from the same project

                if (row % 2 == 0) {
                    component.setBackground(null);
                } else {
                    component.setBackground(grey);
                }
            }

            return component;
        }
    }

    public void setImagingSessionController(ImagingSessionController imagingSessionController) {
        this.imagingSessionController = imagingSessionController;
    }

    public ImagingSessionTableModel getTableModel() {
        return tableModel;
    }

    public TableRowSorter<TableModel> getSorter() {
        return sorter;
    }
}
