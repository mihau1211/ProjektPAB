package DBElements;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Director {
    Scanner scan = new Scanner(System.in);
    String name;
    String surname;
    String birthDate;
    long countryID;
    long directorID;
    String countryName;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public long getCountryID() {
        return countryID;
    }

    public long getDirectorID() {
        return directorID;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString(){
        return directorID + " / " + name + " / " + surname + " / " + birthDate + " / " + countryID;
    }

    public Director(String name, String surname, String birthDate, long countryID, long directorID, String countryName) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.countryID = countryID;
        this.directorID = directorID;
        this.countryName = countryName;
    }

    public Director(){

    }

    public ArrayList<Director> getDirectors (Connection conn){
        ArrayList<Director> directors = new ArrayList<Director>();

        String query = "SELECT * FROM Director, Country WHERE Country_idCountry=idCountry";

        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Director director = new Director(rs.getString("DirectorName"),rs.getString("DirectorSurname"),
                        rs.getString("DirectorBirthDate"),rs.getLong("Country_idCountry"),rs.getLong("idDirector"),
                        rs.getString("CountryName"));
                directors.add(director);
            }
            return directors;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void insertDirector(Connection conn,String name, String surname, String birthDate, long country) throws SQLException {

        String query = " insert into Director (DirectorName, DirectorSurname, DirectorBirthDate, Country_idCountry)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, surname);
        preparedStmt.setString (3, birthDate);
        preparedStmt.setLong (4, country);

        preparedStmt.execute();
    }
    public void deleteDirectorByID(Connection conn, long directorID) throws SQLException {

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
    public void updateDirector(Connection conn, String name, String surname, String birthDate, long countryID, long directorID) throws SQLException{

        String query = "UPDATE Director SET DirectorName = ?, DirectorSurname = ?, DirectorBirthDate = ?, Country_idCountry = ?, " +
                "WHERE idDirector = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, surname);
        preparedStmt.setString(3, birthDate);
        preparedStmt.setLong(4, countryID);
        preparedStmt.setLong(5, directorID);

        preparedStmt.execute();
    }

}
