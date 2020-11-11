package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToSql {
    private final String USER_NAME="";
    private final String PASSWORD="";
    private final String dbms="";
    private final String PORT="";
    private final String CONNECTION_PROPS="";
    private final String SERVER_NAME="";
    public Connection conn = null;
    public void startConnection() {
        System.out.println("Start");

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/filmy?user=root&serverTimezone=UTC");

            System.out.println("Jest połączenie :)");

            //conn.close();


        } catch (
                SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    // dla MySQL connection string 'jdbc:mysql://HOST/DATABASE'

    public Connection getConnection() throws SQLException {

        Connection conn = null;

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmy?user=root&serverTimezone=UTC" +
                this.dbms + "://" + 	this.SERVER_NAME + ":" +
                this.PORT + "/" + CONNECTION_PROPS, USER_NAME, PASSWORD);

        System.out.println("Connected to database");
        return conn;
    }
}

