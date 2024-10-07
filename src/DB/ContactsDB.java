package DB;

import Model.contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import JDBC.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains all observable lists used throughout the project for Contacts.*/

public class ContactsDB {
    /**
     * This method gets a list of contacts
     * @return list of contacts
     * @throws SQLException
     */
    public static ObservableList<contacts> getContacts() throws SQLException {
        ObservableList<contacts> contacts = FXCollections.observableArrayList();

        String all = "SELECT * FROM contacts;";

        DBPS.preparedStatment(JDBC.openConnection(), all);
        PreparedStatement preparedStatement = DBPS.preStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            ;

            // Forward scroll resultSet
            while (resultSet.next()) {
                contacts newContact = new contacts(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );

                contacts.add(newContact);
            }
            return contacts;
        } catch (Exception e) {
            return null;
        }
    }
    /** This method gets a contact by it's name.
     * @param contactName
     * @return the contact
     * @throws SQLException
     */
    public static contacts getContactId(String contactName) throws SQLException {
        String getContId = "SELECT * FROM contacts WHERE Contact_Name=?";

        DBPS.preparedStatment(JDBC.openConnection(), getContId);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, contactName);

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();;

            while (resultSet.next()) {
                contacts newContact = new contacts(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );

                return newContact;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
