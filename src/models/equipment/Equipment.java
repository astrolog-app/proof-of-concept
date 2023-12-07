package models.equipment;

import java.util.HashSet;

public class Equipment {
    private final HashSet<Telescope> telescopes = new HashSet<>();
    private final HashSet<Camera> cameras = new HashSet<>();
    private final HashSet<Mount> mounts = new HashSet<>();
    private final HashSet<Filter> filters = new HashSet<>();
    private final HashSet<Flattener> flatteners = new HashSet<>();
    private final HashSet<Accessoire> accessoires = new HashSet<>();

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

    public HashSet<String> getAllBrands() {
        HashSet<String> brands = new HashSet<>();

        for (Telescope t : telescopes) {
            brands.add(t.getBrand());
        }
        for (Camera c : cameras) {
            brands.add(c.getBrand());
        }
        for (Mount m : mounts) {
            brands.add(m.getBrand());
        }
        for (Filter fi : filters) {
            brands.add(fi.getBrand());
        }
        for (Flattener fl : flatteners) {
            brands.add(fl.getBrand());
        }
        for (Accessoire a : accessoires) {
            brands.add(a.getBrand());
        }

        return brands;
    }
}
