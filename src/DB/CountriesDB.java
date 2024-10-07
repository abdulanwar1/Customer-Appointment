package DB;
import JDBC.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class contains all observable lists used throughout the project for Countries.*/

public class CountriesDB {
    /** This method gets a list countries
     * @return list of countries
     * @throws SQLException
     */
    public static ObservableList<Countries> getCountries() throws SQLException {
        ObservableList<Countries> countries = FXCollections.observableArrayList();

        String all = "SELECT * FROM countries;";

        DBPS.preparedStatment(JDBC.openConnection(), all);
        PreparedStatement preparedStatement = DBPS.preStatement();

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Countries newCountry = new Countries(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );

                countries.add(newCountry);
            }
            return countries;
        } catch (Exception e) {
            return null;
        }
    }

    /** This method gets a country by it's name.
     * @param country
     * @return the country
     * @throws SQLException
     */
    public static Countries getCountId(String country) throws SQLException {

        String getCountId = "SELECT * FROM countries WHERE Country=?";

        DBPS.preparedStatment(JDBC.openConnection(), getCountId);
        PreparedStatement preparedStatement = DBPS.preStatement();

        preparedStatement.setString(1, country);

        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Countries newCountry = new Countries(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );
                return newCountry;
            }

        } catch (Exception e) {
        }
        return null;
    }
    }
