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
            Object[][] tableData = new Object[imagingSessions.size()][loggerColumns.size()];

            int isProgress = 0;
            int lcProgress = 0;

            for (ImagingSession is : imagingSessions) {
                for (LoggerColumns lc : loggerColumns) {
                    switch (lc) {
                        case DATE -> tableData[isProgress][lcProgress] = is.getLightFrame().getDate();
                        case TARGET -> tableData[isProgress][lcProgress] = is.getLightFrame().getTarget();
                        case SUB_LENGTH -> tableData[isProgress][lcProgress] = is.getLightFrame().getSubLength();
                        case TOTAL_SUBS -> tableData[isProgress][lcProgress] = is.getLightFrame().getTotalSubs();
                        case TOTAL_EXPOSURE -> tableData[isProgress][lcProgress] = is.getLightFrame().getTotalSubs() * is.getLightFrame().getSubLength();
                        case INTEGRATED_SUBS -> tableData[isProgress][lcProgress] = is.getLightFrame().getIntegratedSubs();
                        case INTEGRATED_EXPOSURE -> tableData[isProgress][lcProgress] = is.getLightFrame().getIntegratedSubs() * is.getLightFrame().getSubLength();
                        case FILTER -> tableData[isProgress][lcProgress] = is.getLightFrame().getFilter();
                        case GAIN -> tableData[isProgress][lcProgress] = is.getLightFrame().getGain();
                        case OFFSET -> tableData[isProgress][lcProgress] = is.getLightFrame().getOffset();
                        case CAMERA_TEMP -> tableData[isProgress][lcProgress] = is.getLightFrame().getCameraTemp();
                        case OUTSIDE_TEMP -> tableData[isProgress][lcProgress] = is.getLightFrame().getOutsideTemp();
                        case AVERAGE_SEEING -> tableData[isProgress][lcProgress] = is.getLightFrame().getAverageSeeing();
                        case AVERAGE_CLOUD_COVER -> tableData[isProgress][lcProgress] = is.getLightFrame().getAverageCloudCover();
                        case AVERAGE_MOON -> tableData[isProgress][lcProgress] = is.getLightFrame().getAverageMoon();
                        case TELESCOPE -> tableData[isProgress][lcProgress] = is.getLightFrame().getTelescope();
                        case FLATTENER -> tableData[isProgress][lcProgress] = is.getLightFrame().getFlattener();
                        case CAMERA -> tableData[isProgress][lcProgress] = is.getLightFrame().getCamera();
                        case NOTES -> tableData[isProgress][lcProgress] = is.getLightFrame().getNotes();
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
