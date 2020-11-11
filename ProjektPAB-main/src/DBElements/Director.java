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
        return name = scan.nextLine();
    }

    public String getSurname() {
        System.out.println("Podaj nazwisko:");
        return surname = scan.nextLine();
    }

    public String getBirthDate() {
        return birthDate = scan.nextLine();
    }

    public long getCountry() {
        return countryID = scan.nextLong();
    }

    public long getDirectorID() {
        return directorID = scan.nextLong();
    }

    public void insertDirector(Connection conn) throws SQLException {

        String query = " insert into Director (DirectorName, DirectorSurname, DirectorBirthDate, Country_idCountry)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());
        preparedStmt.setString (2, getSurname());
        preparedStmt.setString (3, getBirthDate());
        preparedStmt.setLong (4, getCountry());

        preparedStmt.execute();
    }
    public void deleteDirectorByID(Connection conn, long idDirector) throws SQLException {

        String query = "DELETE FROM director WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idDirector);

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
    public void updateDirector(Connection conn, long idDirector) throws SQLException{

        String query = "UPDATE Director SET DirectorName = ?, DirectorSurname = ?, DirectorBirthDate = ?, Country_idCountry = ?, " +
                "WHERE idDirector = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, getName());
        preparedStmt.setString(1, getSurname());
        preparedStmt.setString(3, getBirthDate());
        preparedStmt.setLong(4, getCountry());
        preparedStmt.setLong(5, idDirector);

        preparedStmt.execute();
    }

}
