package Controller;

import DB.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalTime;
/**

 Controller class for the Combos used throughout the project.
 */
public class Combos {
    public static ObservableList<String> divCombo() throws SQLException {
        ObservableList<String> divisionItems = FXCollections.observableArrayList();


            ObservableList<Division> divisions = DivisionDB.getDivisions();;
            if (divisions != null) {
                for (Division division: divisions) {
                    divisionItems.add(division.getDivision());
                }
            }

        return divisionItems;
    }
    public static ObservableList<String> countCombo() throws SQLException {
        ObservableList<String> countryItems = FXCollections.observableArrayList();


            ObservableList<Countries> countries = CountriesDB.getCountries();;
            if (countries != null) {
                for (Countries country: countries) {
                    countryItems.add(country.getCountry());
                }
            }


        return countryItems;
    }
    public static ObservableList<String> populateTimeComboBoxes() {
        ObservableList<String> time = FXCollections.observableArrayList();
        LocalTime startTime = LocalTime.of(2, 0);
        LocalTime endTime = LocalTime.of(23, 0);

        time.add((startTime.toString()));
        while (startTime.isBefore(endTime)) {
            startTime = startTime.plusMinutes(15);
            time.add((startTime.toString()));
        }

       return time;
    }
    public static ObservableList<String> contCombo() {
        ObservableList<String> contList = FXCollections.observableArrayList();


        ObservableList<contacts> contacts = null;
        try {
            contacts = ContactsDB.getContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (contacts != null){
                for (contacts contact: contacts) {
                    if (!contList.contains(contact.getContactName())) {
                        contList.add(contact.getContactName());
                    }
                }
            }

        return contList;
    }
    public static ObservableList<Integer> custIdCombo()  {
        ObservableList<Integer> custIdList = FXCollections.observableArrayList();


        ObservableList<Customers> customers = null;
        try {
            customers = CustomersDB.getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (customers != null) {
                for (Customers customer : customers) {
                    custIdList.add(customer.getCustomerId());
                }
            }



        return custIdList;
    }

    public static ObservableList<Integer> userIdCombo()  {
        ObservableList<Integer> userList = FXCollections.observableArrayList();


        ObservableList<Users> users = null;
        try {
            users = UsersDB.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users != null) {
                for (Users user: users) {
                    userList.add(user.getUserId());
                }
            }


        return userList;
    }
    public static ObservableList<Integer> ContIdcombo() {
        ObservableList<Integer> contactIDComboList = FXCollections.observableArrayList();

        try {
            ObservableList<contacts> contacts = ContactsDB.getContacts();
            if (contacts != null) {
                for (contacts contact: contacts) {
                    if (!contactIDComboList.contains(contact.getContactId())) {
                        contactIDComboList.add(contact.getContactId());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return contactIDComboList;
    }
     public static ObservableList<Integer> appCombo() {
        ObservableList<Integer> appointmentmentID = FXCollections.observableArrayList();

        try {
            ObservableList<Appointments> appointments = AppointmentsDB.getAppointments();
            if (appointments != null) {
                for (Appointments appointment: appointments) {
                    if (!appointmentmentID.contains(appointment.getAppointmentId())) {
                        appointmentmentID.add(appointment.getAppointmentId());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentmentID;
    }


    public static ObservableList<String> typeC() {
        ObservableList<String> typec = FXCollections.observableArrayList();

        typec.addAll("Consultation", "Planning", "Annual", "Briefing", "Follow-up", "Quarter");
        return typec;
    }
    public static ObservableList<String> monthCombo() {
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("January", "February", "March",
                "April", "May", "June", "July", "August", "September", "October", "November", "December");
        return months;
    }
}
