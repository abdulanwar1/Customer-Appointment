package DB;
import java.sql.*;

/** This class is used throught the project. It contains the prepared statement..*/
public class DBPS {
private static PreparedStatement statement;

    /** This method sets the prepared statement.
     * @param connection
     * @param Statement
     * @throws SQLException
     */
    public static void preparedStatment(Connection connection, String Statement) throws SQLException {
        statement = connection.prepareStatement(Statement);
    }

    /** This method returns the prepared statement.
     * @return statement
     */
    public static PreparedStatement preStatement() {
        return statement;
    }
}
