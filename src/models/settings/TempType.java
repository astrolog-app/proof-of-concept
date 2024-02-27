package models.settings;

public enum TempType {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit");

    private final String name;

    TempType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
