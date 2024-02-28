package models;

import models.imagingFrames.BiasFrame;
import models.imagingFrames.DarkFrame;
import models.imagingFrames.FlatFrame;
import models.imagingFrames.LightFrame;

import java.util.UUID;

public class ImagingSession {
    private UUID id;
    private String folderDir;
    private UUID lightFrameId;
    private UUID flatFrameId;
    private UUID darkFrameId;
    private UUID biasFrameId;

    public ImagingSession() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getFolderDir() {
        return folderDir;
    }
    public void setFolderDir(String folderDir) {
        this.folderDir = folderDir;
    }

    public LightFrame getLightFrame() {
        return null;
    }
    public UUID getLightFrameId() {
        return lightFrameId;
    }
    public void setLightFrameId(UUID lightFrameId) {
        this.lightFrameId = lightFrameId;
    }

    public FlatFrame getFlatFrame() {
        return null;
    }
    public UUID getFlatFrameId() {
        return flatFrameId;
    }
    public void setFlatFrameId(UUID flatFrameId) {
        this.flatFrameId = flatFrameId;
    }

    public DarkFrame getDarkFrame() {
        return null;
    }
    public UUID getDarkFrameId() {
        return darkFrameId;
    }
    public void setDarkFrameId(UUID darkFrameId) {
        this.darkFrameId = darkFrameId;
    }

    public BiasFrame getBiasFrame() {
        return null;
    }
    public UUID getBiasFrameId() {
        return biasFrameId;
    }
    public void setBiasFrameId(UUID biasFrameId) {
        this.biasFrameId = biasFrameId;
    }
}
