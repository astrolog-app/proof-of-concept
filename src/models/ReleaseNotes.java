package models;

import java.util.List;

public class ReleaseNotes {
    private String version;
    private List<String> bulletPoints;

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getBulletPoints() {
        return bulletPoints;
    }
    public void setBulletPoints(List<String> bulletPoints) {
        this.bulletPoints = bulletPoints;
    }
}
