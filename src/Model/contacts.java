package Model;
/**
 This class represents a contact.
 */
public class contacts {
    private int contactId;
    private String contactName;
    private String contactEmail;

    public contacts (int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /** Gets Contact ID
     * @return */
    public int getContactId() {
        return contactId;
    }

    /** Sets Contact ID
     * @param contactId*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** Gets Contact Name
     * @return */
    public String getContactName() {
        return contactName;
    }

    /** Sets Contact Name
     * @param contactName*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** Gets Contact Email
     * @return contactEmail*/
    public String getContactEmail() {
        return contactEmail;
    }

    /** Sets Contact Email
     * @param contactEmail */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}