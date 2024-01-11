package models.license;

public class Licence {
    private final LicenceType licenceType;

    public Licence(LicenceType licenceType) {
        this.licenceType = licenceType;
    }

    public LicenceType getLicenceType() {
        return licenceType;
    }
}
