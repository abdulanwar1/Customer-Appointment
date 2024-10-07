package DB;
import JDBC.JDBC;
import Model.Appointments;
import Model.MonthAppointment;
import Model.contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 *
 * This class contains all CRUD searches and observable list used throughout the project for Appointments.*/
public class AppointmentsDB {
    static LocalDateTime today;
    static contacts contact;
    /**
     * This method gets all appointments by the Contacts contact id.
     * @return list of appointments
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAppointments() throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String all = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID;";

        DBPS.preparedStatment(JDBC.openConnection(), all);
        PreparedStatement preparedStatement = DBPS.preStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointments newAppointment = new Appointments(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     *
     * This method gets a list of appointments for the given month and appointment type.
     *
     * @param selectedMonth
     * @return list appointments for the given month and appointment type
     * @throws SQLException
     */
    public static ObservableList<MonthAppointment> appByMAndT(String selectedMonth) throws SQLException {

        ObservableList<MonthAppointment> MANDT = FXCollections.observableArrayList();

        String mAndT = "SELECT count(Title) as Count,Type,MONTHNAME(Start) as mn,MONTH(Start) as Month from appointments where monthname(Start) = '" + selectedMonth + "' group by MONTH(Start),mn,Type order by Month;";
        DBPS.preparedStatment(JDBC.openConnection(), mAndT);
        PreparedStatement preparedStatement = DBPS.preStatement();


        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            String month = resultSet.getString("Month");
            String type = resultSet.getString("Type");
            int count = resultSet.getInt("Count");

            MonthAppointment MAndT = new MonthAppointment(month, type, count);
            MANDT.add(MAndT);

        }
        return MANDT;
    }



    /**
     * This method gets a list of Appointment by month.
     * @return list of appointments
     * @throws SQLException
     */
    public static ObservableList<Appointments> month() throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        today = LocalDateTime.now();
        LocalDateTime Month = today.minusMonths(1);

        String month = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Start < ? AND Start > ?;";

        DBPS.preparedStatment(JDBC.openConnection(), month);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(Month.toLocalDate()));

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointments newAppointment = new Appointments(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            return null;
        }
    }

    /** This method gets an appointment by its id.
     * @param AppointmentID
     * @return list of appointments by its id
     * @throws SQLException
     */
    public static ObservableList<Appointments> appByAppId(int AppointmentID) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String AppID = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Appointment_ID=?;";

        DBPS.preparedStatment(JDBC.openConnection(), AppID);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setInt(1, AppointmentID);

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointments newAppointment = new Appointments(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );
                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * Adds a new appointment to the database.
     *
     * @param contactName
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userID
     * @throws SQLException
     */
    public static void add(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID) throws SQLException {

        contact = ContactsDB.getContactId(contactName);

        String add = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, Contact_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        DBPS.preparedStatment(JDBC.openConnection(), add);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8, contact.getContactId());
        preparedStatement.setInt(9, userID);

        try {
            preparedStatement.execute();

        } catch (Exception e) {
        }
    }


    /** This method gets a list of Appointments in the last week
     * @return list of appointments
     * @throws SQLException
     */
    public static ObservableList<Appointments> week() throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        today = LocalDateTime.now();
        LocalDateTime Week = today.minusWeeks(1);

        String week = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE Start < ? AND Start > ?;";

        DBPS.preparedStatment(JDBC.openConnection(), week);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(Week.toLocalDate()));

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointments newAppointment = new Appointments(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Deletes an appointment from the database.
     *
     * @param appointmentId
     * @throws SQLException
     */
    public static void delete(int appointmentId) throws SQLException {
        String delete = "DELETE from appointments WHERE Appointment_Id=?";

        DBPS.preparedStatment(JDBC.openConnection(), delete);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setInt(1, appointmentId);

        try {
            preparedStatement.execute();

        } catch (Exception e) {
        }
    }

    /** This method updates an existing appointment by it's id.
     * @param contactName
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userID
     * @param appointmentID
     * @throws SQLException
     */
    public static void update(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID, Integer appointmentID) throws SQLException {
        contact = ContactsDB.getContactId(contactName);

        String Update = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";

        DBPS.preparedStatment(JDBC.openConnection(), Update);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8, contact.getContactId());
        preparedStatement.setInt(9, userID);
        preparedStatement.setInt(10, appointmentID);

        try {
            preparedStatement.execute();

        } catch (Exception e) {

        }
    }


    /** This method gets an appointment with its associated Contact Id
     * @param contactID
     * @return list of appointments by the contact id
     * @throws SQLException
     */
    public static ObservableList<Appointments> appByContId(int contactID) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String contId = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID=c.Contact_ID WHERE a.Contact_ID=?;";

        DBPS.preparedStatment(JDBC.openConnection(), contId);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setInt(1, contactID);

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointments newAppointment = new Appointments(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            return null;
        }
    }


}