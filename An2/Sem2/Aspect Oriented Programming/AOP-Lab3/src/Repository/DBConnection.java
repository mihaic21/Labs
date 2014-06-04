package Repository;


import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * Created by mihai on 4/26/14.
 */
public class DBConnection {

    public DBConnection() {}

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "rhkg38yw4w4rh");

        String urlString = "jdbc:mysql://127.0.0.1:3306/mydb";
        conn = DriverManager.getConnection(urlString, connectionProps);

        return conn;

    }

    public static ResultSet getTable (Connection connection, String table){

        String query = "SELECT * FROM " + table;
        Statement statement;

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}
