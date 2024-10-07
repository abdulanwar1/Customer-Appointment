package Model;
/**
 This class represents a country.
 */
public class Countries {
    private int countryId;
    private String country;


    public Countries (int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /** Gets Country ID
     * @return countryID */
    public int getCountryId() {
        return countryId;
    }

    /** Sets Country ID
     * @param countryId */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** Gets Country
     * @return country */
    public String getCountry() {
        return country;
    }

    /** Sets Country
     * @param country */
    public void setCountry(String country) {
        this.country = country;
    }
}
