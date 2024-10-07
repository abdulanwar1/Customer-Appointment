package Model;
/**
 This class represents a customer.
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String division;
    private String country;
    private int divisionId;


    public Customers(int customerId,
                    String customerName,
                    String address,
                    String postalCode,
                    String phoneNumber,
                    String division,
                    String country,
                    int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.division = division;
        this.country = country;
        this.divisionId = divisionId;
    }

    /**
     * Gets Customer ID
     *
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets Customer ID
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets Customer Name
     *
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets Customer Name
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets Address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets Address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets Postal Code
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets Postal Code
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets Phone Number
     *
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets Phone Number
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets Division
     *
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets Division Name
     *
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets Country Name
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets Country Name
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets Division ID
     *
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets Division ID
     *
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}