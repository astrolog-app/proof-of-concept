package models;

import java.util.EnumMap;

public class ImagingSession {
    EnumMap<LoggerColumns, Object> imagingSessionMap;

    public ImagingSession(EnumMap<LoggerColumns, Object> imagingSessionMap) {
        this.imagingSessionMap = imagingSessionMap;
    }

    public Object getParameter(LoggerColumns loggerColumn) {
        return imagingSessionMap.get(loggerColumn);
    }
    public void setParameter(LoggerColumns loggerColumn, Object object) {
        imagingSessionMap.put(loggerColumn, object);
    }
}
