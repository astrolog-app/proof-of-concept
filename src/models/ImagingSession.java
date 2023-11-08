package models;

import java.time.Instant;

public class ImagingSession {
    private Instant date;
    private String object;

    public ImagingSession(Instant date, String object) {
        this.date = date;
        this.object = object;
    }

    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
}
