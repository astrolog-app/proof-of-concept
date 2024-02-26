package models;

import java.util.List;
import java.util.UUID;

public class ImagingProject {
    private UUID id;
    private List<UUID> imagingSessionIds;

    public ImagingProject() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public List<UUID> getImagingSessionIds() {
        return imagingSessionIds;
    }
    public void setImagingSessionIds(List<UUID> imagingSessionIds) {
        this.imagingSessionIds = imagingSessionIds;
    }
}
