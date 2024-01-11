package models.license;

public class Licence {
    private LicenceType licenceType;
    private String licenceKey;
    private String purchaseDate;

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

    public String getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
