package models.equipment;

import java.util.List;

/**
 * Used for jackson only.
 */
public class EquipmentWrapper {
    private List<Telescope> telescopes;
    private List<Camera> cameras;
    private List<Mount> mounts;
    private List<Filter> filters;
    private List<Flattener> flatteners;

    public List<Telescope> getTelescopes() {
        return telescopes;
    }
    public void setTelescopes(List<Telescope> telescopes) {
        this.telescopes = telescopes;
    }

    public List<Camera> getCameras() {
        return cameras;
    }
    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public List<Mount> getMounts() {
        return mounts;
    }
    public void setMounts(List<Mount> mounts) {
        this.mounts = mounts;
    }

    public List<Filter> getFilters() {
        return filters;
    }
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Flattener> getFlatteners() {
        return flatteners;
    }
    public void setFlatteners(List<Flattener> flatteners) {
        this.flatteners = flatteners;
    }
}
