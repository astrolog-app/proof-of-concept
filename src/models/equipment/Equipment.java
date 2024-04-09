package models.equipment;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.*;

public class Equipment {
    private Map<UUID, Telescope> telescopes = new HashMap<>();
    private Map<UUID, Camera> cameras = new HashMap<>();
    private Map<UUID, Mount> mounts = new HashMap<>();
    private Map<UUID, Filter> filters = new HashMap<>();
    private Map<UUID, Flattener> flatteners = new HashMap<>();
    private Map<UUID, Accessoire> accessoires = new HashMap<>();

    public Map<UUID, Telescope> getTelescopes() {
        return telescopes;
    }
    public void setTelescopes(Map<UUID, Telescope> telescopes) {
        this.telescopes = telescopes;
    }
    public void addTelescope(Telescope telescope) {
        telescopes.put(telescope.getId(), telescope);
    }
    public void removeTelescope(Telescope telescope) {
        telescopes.remove(telescope.getId(), telescope);
    }

    public Map<UUID, Camera> getCameras() {
        return cameras;
    }
    public void setCameras(Map<UUID, Camera> cameras) {
        this.cameras = cameras;
    }
    public void addCamera(Camera camera) {
        cameras.put(camera.getId(), camera);
    }
    public void removeCamera(Camera camera) {
        cameras.remove(camera.getId(), camera);
    }

    public Map<UUID, Mount> getMounts() {
        return mounts;
    }
    public void setMounts(Map<UUID, Mount> mounts) {
        this.mounts = mounts;
    }
    public void addMount(Mount mount) {
        mounts.put(mount.getId(), mount);
    }
    public void removeMount(Mount mount) {
        mounts.remove(mount.getId(), mount);
    }

    public Map<UUID, Filter> getFilters() {
        return filters;
    }
    public void setFilters(Map<UUID, Filter> filters) {
        this.filters = filters;
    }
    public void addFilter(Filter filter) {
        filters.put(filter.getId(), filter);
    }
    public void removeFilter(Filter filter) {
        filters.remove(filter.getId(), filter);
    }

    public Map<UUID, Flattener> getFlatteners() {
        return flatteners;
    }
    public void setFlatteners(Map<UUID, Flattener> flatteners) {
        this.flatteners = flatteners;
    }
    public void addFlattener(Flattener flattener) {
        flatteners.put(flattener.getId(), flattener);
    }
    public void removeFlattener(Flattener flattener) {
        flatteners.remove(flattener.getId(), flattener);
    }

    public Map<UUID, Accessoire> getAccessoires() {
        return accessoires;
    }
    public void setAccessoires(Map<UUID, Accessoire> accessoires) {
        this.accessoires = accessoires;
    }
    public void addAccessoire(Accessoire accessoire) {
        accessoires.put(accessoire.getId(), accessoire);
    }
    public void removeAccessoire(Accessoire accessoire) {
        accessoires.remove(accessoire.getId(), accessoire);
    }

    public void removeEquipmentItem(EquipmentItem e) {
        if (e instanceof Telescope) {
            removeTelescope((Telescope) e);
        }
        if (e instanceof Camera) {
            removeCamera((Camera) e);
        }
        if (e instanceof Mount) {
            removeMount((Mount) e);
        }
        if (e instanceof Filter) {
            removeFilter((Filter) e);
        }
        if (e instanceof Flattener) {
            removeFlattener((Flattener) e);
        }
        if (e instanceof Accessoire) {
            removeAccessoire((Accessoire) e);
        }

    }

    public List<EquipmentItem> createEquipmentItemList() {
        List<EquipmentItem> items = new ArrayList<>();
        items.addAll(telescopes.values());
        items.addAll(cameras.values());
        items.addAll(mounts.values());
        items.addAll(filters.values());
        items.addAll(flatteners.values());
        items.addAll(accessoires.values());

        return items;
    }

    @JsonIgnore
    public HashSet<String> getAllBrands() {
        HashSet<String> brands = new HashSet<>();
        List<EquipmentItem> items = createEquipmentItemList();

        items.forEach(e -> brands.add(e.getBrand()));

        return brands;
    }

    public EquipmentItem getItemFromViewName(String viewName) {
        List<EquipmentItem> items = createEquipmentItemList();

        for (EquipmentItem e : items) {
            if (e.getViewName().equals(viewName)) {
                return e;
            }
        }

        return null;
    }

    public EquipmentType getEquipmentType(EquipmentItem item) {
        if (item instanceof Telescope) {
            return EquipmentType.TELESCOPE;
        }
        if (item instanceof Camera) {
            return EquipmentType.CAMERA;
        }
        if (item instanceof Flattener) {
            return EquipmentType.FLATTENER;
        }
        if (item instanceof Filter) {
            return EquipmentType.FILTER;
        }
        if (item instanceof Mount) {
            return EquipmentType.MOUNT;
        }
        if (item instanceof Accessoire) {
            return EquipmentType.ACCESSOIRE;
        }

        return null;
    }
}
