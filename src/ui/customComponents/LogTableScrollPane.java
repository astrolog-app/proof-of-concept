package ui.customComponents;

import javax.swing.*;

public class LogTableScrollPane extends JPanel {
    public LogTableScrollPane() {
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" },
                { "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        j.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        add(sp);
    }
}
