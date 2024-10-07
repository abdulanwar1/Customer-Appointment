package DB;
import JDBC.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Customers;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class contains all CRUD searches and observable list used throughout the project for Customers.*/

public class CustomersDB {
    /** This method gets customers by Division ID.
     * @return list of Customers
     * @throws SQLException
     */
    public static ObservableList<Customers> getCustomers() throws SQLException {
        ObservableList<Customers> customers = FXCollections.observableArrayList();

        String all = "SELECT * FROM customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.Country_ID=d.COUNTRY_ID;";

        DBPS.preparedStatment(JDBC.openConnection(), all);
        PreparedStatement preparedStatement = DBPS.preStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {

                Customers newCustomer = new Customers(
                        resultSet.getInt("Customer_ID"),
                        resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Postal_Code"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Division"),
                        resultSet.getString("Country"),
                        resultSet.getInt("Division_ID")
                );

                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * This method deletes a customer.
     * @param customerId
     */
    public static void delete(int customerId)
    {
        try {

            String delApp = "DELETE from appointments where Customer_ID = ?";
            DBPS.preparedStatment(JDBC.openConnection(), delApp);
            PreparedStatement preparedStatement = DBPS.preStatement();
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();

            String delCust = "DELETE from customers where Customer_ID = ?";
            DBPS.preparedStatment(JDBC.openConnection(), delCust);
            preparedStatement = DBPS.preStatement();
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /** This method makes a new customer.
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param division
     * @throws SQLException
     */
    public static void add(String name, String address, String postalCode, String phone, String division) throws SQLException {
        Division Div = DivisionDB.getDivId(division);
        String add = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

        DBPS.preparedStatment(JDBC.openConnection(), add);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, Div.getDivisionId());

        try {
            preparedStatement.execute();
        } catch (Exception e) {
        }
    }

    /** This method updates a customer.
     * @param customerId
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param division
     * @throws SQLException
     */
    public static void update(int customerId, String name, String address, String postalCode, String phone, String division) throws SQLException {
        Division Div = DivisionDB.getDivId(division);

        String insertStatement = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";

        DBPS.preparedStatment(JDBC.openConnection(), insertStatement);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, Div.getDivisionId());
        preparedStatement.setInt(6, customerId);

        try {
            preparedStatement.execute();
        } catch (Exception e) {
        }
    }


}
