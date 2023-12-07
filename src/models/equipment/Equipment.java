package models.equipment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Equipment {
    private HashSet<Telescope> telescopes = new HashSet<>();
    private HashSet<Camera> cameras = new HashSet<>();
    private HashSet<Mount> mounts = new HashSet<>();
    private HashSet<Filter> filters = new HashSet<>();
    private HashSet<Flattener> flatteners = new HashSet<>();
    private HashSet<Accessoire> accessoires = new HashSet<>();

    public HashSet<Telescope> getTelescopes() {
        return telescopes;
    }
    public void addTelescope(Telescope telescope) {
        telescopes.add(telescope);
    }
    public void removeTelescope(Telescope telescope) {
        telescopes.remove(telescope);
    }

    public HashSet<Camera> getCameras() {
        return cameras;
    }
    public void addCamera(Camera camera) {
        cameras.add(camera);
    }
    public void removeCamera(Camera camera) {
        cameras.remove(camera);
    }

    public HashSet<Mount> getMounts() {
        return mounts;
    }
    public void addMount(Mount mount) {
        mounts.add(mount);
    }
    public void removeMount(Mount mount) {
        mounts.remove(mount);
    }

    public HashSet<Filter> getFilters() {
        return filters;
    }
    public void addFilter(Filter filter) {
        filters.add(filter);
    }
    public void removeFilter(Filter filter) {
        filters.remove(filter);
    }

    public HashSet<Flattener> getFlatteners() {
        return flatteners;
    }
    public void addFlattener(Flattener flattener) {
        flatteners.add(flattener);
    }
    public void removeFlattener(Flattener flattener) {
        flatteners.remove(flattener);
    }

    public HashSet<Accessoire> getAccessoires() {
        return accessoires;
    }
    public void addAccessoire(Accessoire accessoire) {
        accessoires.add(accessoire);
    }
    public void removeAccessoire(Accessoire accessoire) {
        accessoires.remove(accessoire);
    }

    public List<String> getAllBrands() {
        List<String> brands = new ArrayList<>();

        for (Telescope t : telescopes) {
            brands.add(t.getBrand());
        }
        for (Camera c : cameras) {
            brands.add(c.getBrand());
        }

        return brands;
    }
}
