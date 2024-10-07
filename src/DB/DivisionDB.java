package DB;
import JDBC.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Countries;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class contains all CRUD searches and observable list used throughout the project for Divisions.*/
public class DivisionDB {
    static Countries newCountry;
    /** This method gets all the divisions.
     * @return List of divisions
     * @throws SQLException
     */
    public static ObservableList<Division> getDivisions() throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String all = "SELECT * FROM first_level_divisions;";

        DBPS.preparedStatment(JDBC.openConnection(), all);
        PreparedStatement preparedStatement = DBPS.preStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );

                divisions.add(newDivision);
            }
            return divisions;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * This method gets divisions by the country associated with it.
     * @param country
     * @return list of divisions
     * @throws SQLException
     */
    public static ObservableList<Division> getDivByCount(String country) throws SQLException {
        newCountry = CountriesDB.getCountId(country);
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String getDivByCount = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID=?;";

        DBPS.preparedStatment(JDBC.openConnection(), getDivByCount);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setInt(1, newCountry.getCountryId());

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );

                divisions.add(newDivision);
            }
            return divisions;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method gets a division by its name
     * @param division
     * @return list of divisions
     * @throws SQLException
     */
    public static Division getDivId(String division) throws SQLException {
        String getDivId = "SELECT * FROM first_level_divisions WHERE Division=?";

        DBPS.preparedStatment(JDBC.openConnection(), getDivId);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, division);

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("COUNTRY_ID")
                );
                return newDivision;
            }

        } catch (Exception e) {
        }
        return null;
    }


}
