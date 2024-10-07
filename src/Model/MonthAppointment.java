package Model;
/**
 This class represents a Month Appointment.
 */public class MonthAppointment {

    private String month;
    private String type;
    private int count;

    public MonthAppointment(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }
    /** Gets Month
     * @return month */
    public String getMonth() {
        return month;
    }
    /** Gets type
     * @return type */
    public String getType() {
        return type;
    }
    /** Gets count
     * @return count */
    public int getCount() {
        return count;
    }
}