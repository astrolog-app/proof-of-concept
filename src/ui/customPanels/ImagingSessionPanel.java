package ui.customPanels;

import models.ImagingSession;
import models.LoggerColumns;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ImagingSessionPanel extends JPanel {
    public ImagingSessionPanel(List<LoggerColumns> loggerColumns, ImagingSession imagingSession) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
}
