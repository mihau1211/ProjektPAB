package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Director {
    Scanner scan = new Scanner(System.in);
    String name;
    String surname;
    String birthDate;
    long countryID;
    long directorID;

    public String getName() {
        System.out.println("Podaj imie:");
        return name = scan.nextLine();
    }

    public String getSurname() {
        System.out.println("Podaj nazwisko:");
        return surname = scan.nextLine();
    }

    public String getBirthDate() {
        System.out.println("Podaj date urodzenia:");
        return birthDate = scan.nextLine();
    }

    public long getCountryID() {
        System.out.println("Podaj ID kraju:");
        return countryID = scan.nextLong();
    }

    public long getDirectorID() {
        System.out.println("Podaj ID:");
        return directorID = scan.nextLong();
    }

    public void insertDirector(Connection conn) throws SQLException {

        String query = " insert into Director (DirectorName, DirectorSurname, DirectorBirthDate, Country_idCountry)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());
        preparedStmt.setString (2, getSurname());
        preparedStmt.setString (3, getBirthDate());
        preparedStmt.setLong (4, getCountryID());

        preparedStmt.execute();
    }
    public void deleteDirectorByID(Connection conn) throws SQLException {

        directorID=getDirectorID();
        String query = "DELETE FROM director WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, directorID);

        preparedStmt.execute();
    }
    public void deleteDirectorByName(Connection conn, String DirectorName) throws SQLException{
        String query = "DELETE FROM director WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, DirectorName);

        preparedStmt.execute();
    }
    public void selectDirector(Connection conn) throws SQLException {

        String query = "SELECT idDirector, DirectorName, DirectorSurname, DirectorBirthDate, CountryName FROM Director, Country WHERE Country_idCountry=idCountry";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("=============");
        System.out.println("||DIRECTORS||");
        System.out.println("=============");

        System.out.println("| ID  |       NAME         |       SURNAME         |    YEAR   |   COUNTRY   |");

        while (rs.next()){
            long id = rs.getLong("idDirector");
            String name = rs.getString("DirectorName");
            String surname = rs.getString("DirectorSurname");
            String year = rs.getString("DirectorBirthDate");
            String country = rs.getString("CountryName");
            System.out.format("|%1$-5s|%2$-20s|%3$-23s|%4$-11s|%5$-13s|\n", id, name, surname, year, country);
        }
    }
    public void updateDirector(Connection conn) throws SQLException{

        String query = "UPDATE Director SET DirectorName = ?, DirectorSurname = ?, DirectorBirthDate = ?, Country_idCountry = ?, " +
                "WHERE idDirector = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, getName());
        preparedStmt.setString(1, getSurname());
        preparedStmt.setString(3, getBirthDate());
        preparedStmt.setLong(4, getCountryID());
        preparedStmt.setLong(5, getDirectorID());

        preparedStmt.execute();
    }

}
