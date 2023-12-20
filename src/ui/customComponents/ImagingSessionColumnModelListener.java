package ui.customComponents;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

public class ImagingSessionColumnModelListener implements TableColumnModelListener {
    @Override
    public void columnAdded(TableColumnModelEvent e) {
        System.out.println("Column added: " + e.getFromIndex());
    }

    @Override
    public void columnRemoved(TableColumnModelEvent e) {
        System.out.println("Column removed: " + e.getFromIndex());
    }

    @Override
    public void columnMoved(TableColumnModelEvent e) {
        System.out.println("Column moved: " + e.getFromIndex() + " to " + e.getToIndex());
    }

    @Override
    public void columnMarginChanged(ChangeEvent e) {
        // This method is called when the width of a column changes
    }

    @Override
    public void columnSelectionChanged(ListSelectionEvent e) {
        // This method is called when the selection of columns changes
    }
}
