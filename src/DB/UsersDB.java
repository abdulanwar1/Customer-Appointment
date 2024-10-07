package DB;
import JDBC.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class contains the observable lists and validation used throughout the project for Contacts.*/

public class UsersDB {
    /** This method gets all users
     * @return list of users
     * @throws SQLException
     */
    public static ObservableList<Users> getUsers() throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();

        String all = "SELECT * FROM users;";

        DBPS.preparedStatment(JDBC.openConnection(), all);
        PreparedStatement preparedStatement = DBPS.preStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            ;

            while (resultSet.next()) {
                Users newUser = new Users(
                        resultSet.getInt("User_ID"),
                        resultSet.getString("User_Name"),
                        resultSet.getString("Password")
                );

                users.add(newUser);
            }
            return users;
        } catch (Exception e) {
            return null;
        }
    }
    /** This method validates login credentials.
     * @param username Username
     * @param password Password
     * @return Boolean Returns true if user matches and false if not
     * @throws SQLException
     */
    public static boolean goodUser(String username, String password) throws SQLException {
        String userCheck = "SELECT * FROM users WHERE User_Name=? AND Password=?";

        DBPS.preparedStatment(JDBC.openConnection(), userCheck);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return (resultSet.next());
        } catch (Exception e) {
            return false;
        }
    }


}
