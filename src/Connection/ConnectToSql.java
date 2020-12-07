package Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

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

        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        hashtable.put("host", "127.0.0.1");
        hashtable.put("user", "root");

        System.out.println(hashtable.get("user"));

        Properties applicationProps = new Properties();
        applicationProps.setProperty("host", "127.0.0.1");
        applicationProps.setProperty("port", "55555");
        applicationProps.setProperty("user", "root");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("appProperties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            applicationProps.storeToXML(out, "---No Comment---");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream("appProperties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            applicationProps.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(applicationProps.getProperty("host", "127.0.0.1"));

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

