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
        System.out.println("===========");
        System.out.println("||COUNTRY||");
        System.out.println("===========");
        System.out.println(" |ID|        NAME|");
        while (rs.next()){
            long id = rs.getLong("idCountry");
            String name = rs.getString("CountryName");
            System.out.format("|%1$-5s|%2$-20s|\n", id, name);
        }
    }
    public void updateCountry(Connection conn, long idCountry, String CountryName) throws SQLException{

        String query = "UPDATE Country SET CountryName = ? WHERE idCOuntry = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, CountryName);
        preparedStmt.setLong(2, idCountry);

        preparedStmt.execute();
    }


    public void addCountries(Connection conn) throws SQLException {
        String query = "insert into Country (countryName)"
                + " values ('POLAND')";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('ENGLAND')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('SCOTLAND')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('USA')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('GERMANY')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('RUSSIA')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('BELGIUM')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = "insert into Country (countryName)"
                + " values ('FRANCE')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();
    }
}