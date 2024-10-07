package Model;
/**
 This class represents a division.
 */
public class Division {
    private int divisionId;
    private String division;
    private int countryId;

    public Division (int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /** Gets Division ID
     * @return divisionId */
    public int getDivisionId() {
        return divisionId;
    }

    /** Sets Division ID
     * @param divisionId */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** Gets Division
     * @return division  */
    public String getDivision() {
        return division;
    }

    /** Sets Division
     * @param division */
    public void setDivision(String division) {
        this.division = division;
    }

    /** Gets Country ID
     * @return countryId */
    public int getCountryId() {
        return countryId;
    }

    /** Sets Country ID
     * @param countryId */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}