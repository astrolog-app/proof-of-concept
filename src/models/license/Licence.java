package models.license;

public class Licence {
    private LicenceType licenceType;
    private String licenceKey;
    private String computerName;

    public Licence() {}

    public LicenceType getLicenceType() {
        return licenceType;
    }
    public void setLicenceType(LicenceType licenceType) {
        this.licenceType = licenceType;
    }

    public String getLicenceKey() {
        return licenceKey;
    }
    public void setLicenceKey(String licenceKey) {
        this.licenceKey = licenceKey;
    }

    public String getComputerName() {
        return computerName;
    }
    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }
}
