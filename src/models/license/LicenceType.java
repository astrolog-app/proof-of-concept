package models.license;

public enum LicenceType {
    LITE("Lite"),
    STANDARD("Standard"),
    PRO("Pro");
    private final String name;

    LicenceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LicenceType getEnum(String name) {
        for (LicenceType licenceType : LicenceType.values()) {
            if (licenceType.getName().equalsIgnoreCase(name)) {
                return licenceType;
            }
        }

        return null;
    }
}
