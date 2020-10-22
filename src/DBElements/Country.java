package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Country {
    Scanner scan = new Scanner(System.in);
    String name;
    long countryID;

    public String getName() {
        return name = scan.nextLine();
    }

    public long getCountryID() {
        return countryID = scan.nextLong();
    }

    public void insertCountry(Connection conn) throws SQLException {

        String query = " insert into Country (countryName)"
                + " values (?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());

        preparedStmt.execute();
    }
    public void deleteCountryByID(Connection conn, long idCountry) throws SQLException {

        String query = "DELETE FROM country WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idCountry);

        preparedStmt.execute();
    }
    public void deleteCountryByName(Connection conn, String CountryName) throws SQLException{
        String query = "DELETE FROM country WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, CountryName);

        preparedStmt.execute();
    }
    public void selectCountry(Connection conn) throws SQLException {

        String query = "SELECT * FROM Country";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            long id = rs.getLong("idCountry");
            String name = rs.getString("CountryName");
            System.out.format("%s, %s\n", id, name);
        }
    }
    public void updateCountry(Connection conn, long idCountry, String CountryName) throws SQLException{

        String query = "UPDATE Country SET CountryName = ? WHERE idCOuntry = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, CountryName);
        preparedStmt.setLong(2, idCountry);

        preparedStmt.execute();
    }

}
