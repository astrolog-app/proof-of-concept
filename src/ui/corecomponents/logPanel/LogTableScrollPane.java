package ui.corecomponents.logPanel;

import models.ImagingSession;
import models.LoggerColumns;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LogTableScrollPane extends JScrollPane {
    public LogTableScrollPane(List<LoggerColumns> loggerColumns, List<ImagingSession> imagingSessions) {
        JPanel logTablePanel = new JPanel();
//        logTablePanel.setLayout(new GridLayout(0, loggerColumns.size()));
        logTablePanel.setLayout(new GridLayout(0, 1));

        for (ImagingSession imagingSession : imagingSessions) {
            logTablePanel.add(new ImagingSessionPanel(loggerColumns, imagingSession));
        }

        add(logTablePanel);
//        setVisible(true);
    }
}
