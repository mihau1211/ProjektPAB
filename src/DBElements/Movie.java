package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Movie {
    Scanner scan = new Scanner(System.in);
    String name;
    String year;
    int rating;
    long typeID;
    long directorID;
    long countryID;
    long movieID;

    public String getName() {
        System.out.println("Podaj nazwe filmu:");
        return name = scan.nextLine();
    }

    public String getYear() {
        System.out.println("Podaj rok produkcji:");
        return year = scan.nextLine();
    }

    public int getRating() {
        System.out.println("Podaj ocene:");
        return rating = scan.nextInt();
    }

    public long getTypeID() {
        System.out.println("Podaj id gatunku:");
        return typeID = scan.nextLong();
    }

    public long getDirectorID() {
        System.out.println("Podaj rezysera:");
        return directorID = scan.nextLong();
    }

    public long getCountry() {
        System.out.println("Podaj id kraju produkcji:");
        return countryID = scan.nextLong();
    }

    public long getMovieID() {
        return movieID = scan.nextLong();
    }

    public void insertMovie(Connection conn) throws SQLException {

        String query = " insert into Movie (movieName, movieYear, movieRating, Country_idCountry, Type_idType, " +
                "Director_idDirector)"
                + " values (?,?,?,?,?,?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());
        preparedStmt.setString (2, getYear());
        preparedStmt.setInt   (3, getRating());
        preparedStmt.setLong(4, getCountry());
        preparedStmt.setLong(5, getTypeID());
        preparedStmt.setLong(6, getDirectorID());

        preparedStmt.execute();
    }
    public void deleteMovieByID(Connection conn) throws SQLException {

        movieID=getMovieID();

        String query1 = "DELETE FROM Actor_Has_Movie WHERE Movie_idMovie=?;";
        String query2 = "DELETE FROM Movie WHERE idMovie=?;";

        PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
        preparedStmt1.setLong(1, movieID);
        preparedStmt1.execute();

        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setLong (1, movieID);
        preparedStmt2.execute();
    }
    public void deleteMovieByName(Connection conn, String MovieName) throws SQLException{
        String query = "DELETE FROM Movie WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, MovieName);

        preparedStmt.execute();
    }
    public void selectMovie(Connection conn) throws SQLException {

        String query = "SELECT * FROM Movie";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            long id = rs.getLong("idMovie");
            String name = rs.getString("MovieName");
            String year = rs.getString("MovieYear");
            int rating = rs.getInt("MovieRating");
            System.out.format("| %s | ---------- |   %s    | ---------- | %s | ---------- | %s |\n",id,  rating, name, year);
        }
    }
    public void updateMovie(Connection conn, long idMovie) throws SQLException{

        String query = "UPDATE Movie SET MovieName = ?, MovieYear = ?, MovieRating = ?, Country_idCountry = ?," +
                " Type_idType = ?, Director_idDirector = ? WHERE idMovie = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, getName());
        preparedStmt.setString(2, getYear());
        preparedStmt.setInt(3, getRating());
        preparedStmt.setLong(4, getCountry());
        preparedStmt.setLong(5, getTypeID());
        preparedStmt.setLong(6, getDirectorID());
        preparedStmt.setLong(7, 1);

        preparedStmt.execute();
    }

}
