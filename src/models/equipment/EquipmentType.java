package models.equipment;

public enum EquipmentType {
    TELESCOPE("Telescope"),
    CAMERA("Camera"),
    FLATTENER("Flattener"),
    FILTER("Filter"),
    MOUNT("Mount"),
    ACCESSOIRE("Accessoire");
    private final String name;

    EquipmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
