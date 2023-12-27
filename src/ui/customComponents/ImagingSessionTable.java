package ui.customComponents;

import controllers.ImagingSessionController;
import models.imagingSessionTable.ImagingSessionColumnModelListener;
import models.imagingSessionTable.ImagingSessionTableModel;
import models.settings.LoggerColumns;
import models.equipment.Equipment;
import models.settings.ImagingSessionConfig;
import services.fileHandler.ConfigurationStore;
import ui.corecomponents.LogPanel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
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
    private final ImagingSessionTableModel tableModel;
    private TableRowSorter<TableModel> sorter;

    public ImagingSessionTable(Equipment equipment, LogPanel logPanel) {
        isConfig = ConfigurationStore.loadImagingSessionConfig();
        this.logPanel = logPanel;
        this.equipment = equipment;
        this.tableModel = new ImagingSessionTableModel();

        createTable();
        setColumnsWidth();
//        sortRows();
        handleActions();
    }

    private void createTable() {
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(tableModel);

        TableColumnModel columnModel = getColumnModel();
        columnModel.addColumnModelListener(new ImagingSessionColumnModelListener());

        setRowHeight(45);
        showHorizontalLines = true;
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
        LoggerColumns defaultSortedColumns;
        SortOrder columnSortingType;
        if (isConfig != null) {
            defaultSortedColumns = isConfig.getDefaultSortedColumn();
            columnSortingType = isConfig.getColumnSortingType();
        } else  {
            defaultSortedColumns = LoggerColumns.DATE;
            columnSortingType = SortOrder.DESCENDING;
        }
        int defaultSortedColumnInt = tableModel.getColumnAt(defaultSortedColumns);

        setAutoCreateRowSorter(true);
        sorter = (TableRowSorter<TableModel>) getRowSorter();
        updateSortComparators();
        sorter.setSortKeys(List.of(new RowSorter.SortKey(defaultSortedColumnInt, columnSortingType)));
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
        getSelectionModel().addListSelectionListener(e -> {
            logPanel.updateTableButtonState();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = getSelectedRow();
                    if (selectedRow != -1) {
                        imagingSessionController.showImagingSessionDetails(tableModel.getSession(getSelectedRow()));
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

    private JPopupMenu createPopupMenu() {
        boolean enableAll = tableModel.getSession(getSelectedRow()) != null;
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem1 = new JMenuItem("Add From Existing Folder");
        menuItem1.addActionListener((ActionEvent e) -> imagingSessionController.addImagingSessionAutomatically());
        popupMenu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Add Manually");
        menuItem2.addActionListener((ActionEvent e) -> imagingSessionController.addImagingSessionManually(equipment));
        popupMenu.add(menuItem2);

        JMenuItem menuItem3  = new JMenuItem("Show Details");
        menuItem3.addActionListener((ActionEvent e) -> imagingSessionController.showImagingSessionDetails(tableModel.getSession(getSelectedRow())));
        menuItem3.setEnabled(enableAll);
        popupMenu.add(menuItem3);

        JMenuItem menuItem4 = new JMenuItem("Edit");
        menuItem4.addActionListener((ActionEvent e) -> imagingSessionController.editImagingSession(equipment, tableModel.getSession(getSelectedRow())));
        menuItem4.setEnabled(enableAll);
        popupMenu.add(menuItem4);

        JMenuItem menuItem5 = new JMenuItem("Delete");
        menuItem5.addActionListener((ActionEvent e) -> imagingSessionController.removeImagingSession());
        menuItem5.setEnabled(enableAll);
        popupMenu.add(menuItem5);

        return popupMenu;
    }

    public void setImagingSessionController(ImagingSessionController imagingSessionController) {
        this.imagingSessionController = imagingSessionController;
    }

    public ImagingSessionTableModel getTableModel() {
        return tableModel;
    }
}
