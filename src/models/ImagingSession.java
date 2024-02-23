package models;

import models.imagingFrames.*;

import java.util.List;
import java.util.UUID;

public class ImagingSession {
    private String projectID;
    private String folderDir;
    private LightFrame lightFrame;
    private FlatFrame flatFrame;
    private UUID darkFrameId;
    private UUID biasFrameId;

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

    public DarkFrame getDarkFrame(List<CalibrationFrame> calibrationFrames) {
        for (CalibrationFrame c : calibrationFrames) {
            if (c.getId() == darkFrameId) {
                return (DarkFrame) c;
            }
        }

        return null;
    }
    public FlatFrame getFlatFrame() {
        return flatFrame;
    }
    public void setFlatFrame(FlatFrame flatFrame) {
        this.flatFrame = flatFrame;
    }

    public UUID getDarkFrameId() {
        return darkFrameId;
    }
    public void setDarkFrameId(UUID darkFrameId) {
        this.darkFrameId = darkFrameId;
    }

    public BiasFrame getBiasFrame(List<CalibrationFrame> calibrationFrames) {
        for (CalibrationFrame c : calibrationFrames) {
            if (c.getId() == biasFrameId) {
                return (BiasFrame) c;
            }
        }

        return null;
    }
    public UUID getBiasFrameId() {
        return biasFrameId;
    }
    public void setBiasFrameId(UUID biasFrameId) {
        this.biasFrameId = biasFrameId;
    }
}
