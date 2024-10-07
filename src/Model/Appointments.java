package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
  This class represents an appointment.
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDate endDate;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;

    public Appointments(int appointmentId,
                       String title,
                       String description,
                       String location,
                       String type,
                       LocalDate startDate,
                       LocalDateTime startTime,
                       LocalDate endDate,
                       LocalDateTime endTime,
                       int customerId,
                       int userId,
                       int contactId,
                       String contactName
    ) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /** Gets Appointment ID
     * @return appointmentId */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** Sets Appointment ID
     * @param appointmentId */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** Gets Appointment Title
     * @return title  */
    public String getTitle() {
        return title;
    }

    /** Sets Appointment Title
     * @param title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Gets Appointment Description
     * @return description*/
    public String getDescription() {
        return description;
    }

    /** Sets Appointment Description
     * @param description */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Gets Appointment Location
     * @return location */
    public String getLocation() {
        return location;
    }

    /** Sets Appointment Location
     * @param location */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Gets Appointment Type
     * @return type */
    public String getType() {
        return type;
    }

    /** Sets Appointment Type
     * @param type */
    public void setType(String type) {
        this.type = type;
    }

    /** Gets Start Date
     * @return startDate */
    public LocalDate getStartDate() {
        return startDate;
    }

    /** Sets Appointment Start Date
     * @param startDate */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /** Gets Start Time
     * @return startTime */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /** Sets Appointment Start Time
     * @param startTime */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /** Gets End Date
     * @return endDate */
    public LocalDate getEndDate() {
        return endDate;
    }

    /** Sets Appointment End Date
     * @param endDate */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /** Gets End Time
     * @return endTime */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /** Sets Appointment End Time
     * @param endTime */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /** Gets Customer ID
     * @return customerId*/
    public int getCustomerId() {
        return customerId;
    }

    /** Sets Customer ID
     * @param customerId */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Gets User ID
     * @return userId */
    public int getUserId() {
        return userId;
    }

    /** Sets User ID
     * @param userId */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Gets Contact ID
     * @return contactId */
    public int getContactId() {
        return contactId;
    }

    /** Sets Contact ID
     * @param contactId */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** Gets Contact Name
     * @return contactName*/
    public String getContactName() {
        return contactName;
    }

    /** Sets Contact Name
     * @param contactName */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}