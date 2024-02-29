package models;

import java.util.List;

public class ReleaseNotes {
    private boolean updated;
    private boolean show;
    private String version;
    private String releaseDate;
    private List<String> features;
    private List<String> bugFixes;

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

    public List<String> getFeatures() {
        return features;
    }
    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getBugFixes() {
        return bugFixes;
    }
    public void setBugFixes(List<String> bugFixes) {
        this.bugFixes = bugFixes;
    }
}
