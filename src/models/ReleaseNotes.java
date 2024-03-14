package models;

import java.util.List;
import java.util.Map;

public class ReleaseNotes {
    private boolean updated;
    private boolean show;
    private String version;
    private String releaseDate;
    private Map<String, List<String>> description;

    public boolean getUpdated() {
        return updated;
    }
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean getShow() {
        return show;
    }
    public void setShow(boolean show) {
        this.show = show;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Map<String, List<String>> getDescription() {
        return description;
    }
    public void setDescription(Map<String, List<String>> description) {
        this.description = description;
    }
}
