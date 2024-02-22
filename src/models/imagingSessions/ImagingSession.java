package models.imagingSessions;

import models.imagingSessions.calibrationFrames.BiasFrame;
import models.imagingSessions.calibrationFrames.DarkFrame;

public class ImagingSession {
    private String projectID;
    private String folderDir;
    private LightFrame lightFrame;
    private DarkFrame darkFrame;
    private FlatFrame flatFrame;
    private BiasFrame biasFrame;

    public String getProjectID() {
        return projectID;
    }
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getFolderDir() {
        return folderDir;
    }
    public void setFolderDir(String folderDir) {
        this.folderDir = folderDir;
    }

    public LightFrame getLightFrame() {
        return lightFrame;
    }
    public void setLightFrame(LightFrame lightFrame) {
        this.lightFrame = lightFrame;
    }

    public DarkFrame getDarkFrame() {
        return darkFrame;
    }
    public void setDarkFrame(DarkFrame darkFrame) {
        this.darkFrame = darkFrame;
    }

    public FlatFrame getFlatFrame() {
        return flatFrame;
    }
    public void setFlatFrame(FlatFrame flatFrame) {
        this.flatFrame = flatFrame;
    }

    public BiasFrame getBiasFrame() {
        return biasFrame;
    }
    public void setBiasFrame(BiasFrame biasFrame) {
        this.biasFrame = biasFrame;
    }
}
