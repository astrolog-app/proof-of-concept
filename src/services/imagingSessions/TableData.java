package services.imagingSessions;

import models.imagingSessions.ImagingSession;
import models.settings.LoggerColumns;
import services.fileHandler.ImagingSessionStore;

import java.lang.annotation.Target;
import java.util.List;

public class TableData {
    public Object[][] generateTableData(List<LoggerColumns> loggerColumns) {
        List<ImagingSession> imagingSessions = ImagingSessionStore.loadImagingSessions();

        if (imagingSessions != null) {
            Object[][] tableData = new Object[loggerColumns.size()][imagingSessions.size()];

            int isProgress = 0;
            int lcProgress = 0;

            for (ImagingSession is : imagingSessions) {
                for (LoggerColumns lc : loggerColumns) {
                    switch (lc) {
                        case DATE -> tableData[lcProgress][isProgress] = is.getLightFrame().getDate();
                        case TARGET -> tableData[lcProgress][isProgress] = is.getLightFrame().getTarget();
                        case SUB_LENGTH -> tableData[lcProgress][isProgress] = is.getLightFrame().getSubLength();
                        case TOTAL_SUBS -> tableData[lcProgress][isProgress] = is.getLightFrame().getTotalSubs();
                        case TOTAL_EXPOSURE -> tableData[lcProgress][isProgress] = is.getLightFrame().getTotalSubs() * is.getLightFrame().getSubLength();
                        case INTEGRATED_SUBS -> tableData[lcProgress][isProgress] = is.getLightFrame().getIntegratedSubs();
                        case INTEGRATED_EXPOSURE -> tableData[lcProgress][isProgress] = is.getLightFrame().getIntegratedSubs() * is.getLightFrame().getSubLength();
                        case FILTER -> tableData[lcProgress][isProgress] = is.getLightFrame().getFilter();
                        case GAIN -> tableData[lcProgress][isProgress] = is.getLightFrame().getGain();
                        case OFFSET -> tableData[lcProgress][isProgress] = is.getLightFrame().getOffset();
                        case CAMERA_TEMP -> tableData[lcProgress][isProgress] = is.getLightFrame().getCameraTemp();
                        case OUTSIDE_TEMP -> tableData[lcProgress][isProgress] = is.getLightFrame().getOutsideTemp();
                        case AVERAGE_SEEING -> tableData[lcProgress][isProgress] = is.getLightFrame().getAverageSeeing();
                        case AVERAGE_CLOUD_COVER -> tableData[lcProgress][isProgress] = is.getLightFrame().getAverageCloudCover();
                        case AVERAGE_MOON -> tableData[lcProgress][isProgress] = is.getLightFrame().getAverageMoon();
                        case TELESCOPE -> tableData[lcProgress][isProgress] = is.getLightFrame().getTelescope();
                        case FLATTENER -> tableData[lcProgress][isProgress] = is.getLightFrame().getFlattener();
                        case CAMERA -> tableData[lcProgress][isProgress] = is.getLightFrame().getCamera();
                        case NOTES -> tableData[lcProgress][isProgress] = is.getLightFrame().getNotes();
                    }
                    lcProgress++;
                }
                lcProgress = 0;
                isProgress++;
            }
            return tableData;
        } else {
            return null;
        }
    }
}
