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
}
