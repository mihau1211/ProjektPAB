package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Director {
    Scanner scan = new Scanner(System.in);
    String name;
    String birthDate;
    long countryID;
    long directorID;

    public String getName() {
        return name = scan.nextLine();
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

    public void insertDirector(Connection conn, String DirectorName, String DirectorBirthDate,
                            long Country_idCountry) throws SQLException {

        String query = " insert into Director (DirectorName, DirectorBirthDate, Country_idCountry)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, DirectorName);
        preparedStmt.setString (2, DirectorBirthDate);
        preparedStmt.setLong (3, Country_idCountry);

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

        String query = "SELECT * FROM Director";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("=============");
        System.out.println("||DIRECTORS||");
        System.out.println("=============");

        System.out.println("|ID|   |NAME|     |YEAR|");

        while (rs.next()){
            long id = rs.getLong("idDirector");
            String name = rs.getString("DirectorName");
            String year = rs.getString("DirectorBirthDate");
            System.out.format("|%1$-5s|%2$-20s|%3$-11s|\n", id, name, year);
        }
    }
    public void updateDirector(Connection conn, long idDirector, String DirectorName,
                               String DirectorBirthDate, long Country_idCountry) throws SQLException{

        String query = "UPDATE Director SET DirectorName = ?, DirectorBirthDate = ?, Country_idCountry = ?, " +
                "WHERE idDirector = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, DirectorName);
        preparedStmt.setString(2, DirectorBirthDate);
        preparedStmt.setLong(3, Country_idCountry);
        preparedStmt.setLong(4, idDirector);

        preparedStmt.execute();
    }

}
