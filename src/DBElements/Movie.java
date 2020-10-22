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
        return name = scan.nextLine();
    }

    public String getYear() {
        return year = scan.nextLine();
    }

    public int getRating() {
        return rating = scan.nextInt();
    }

    public long getTypeID() {
        return typeID = scan.nextLong();
    }

    public long getDirectorID() {
        return directorID = scan.nextLong();
    }

    public long getCountry() {
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

        String query = "DELETE FROM Movie WHERE idMovie=?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, getMovieID());

        preparedStmt.execute();
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
            System.out.format("%s, %s, %s, %s\n", id, name, year, rating);
        }
    }
    public void updateMovie(Connection conn, long idMovie, String MovieName, String MovieYear, long Country_idCountry,
                            long Type_idType, long Director_idDirector) throws SQLException{

        String query = "UPDATE Actor SET MovieName = ?, MovieYear = ?, Country_idCountry = ?, Type_idType = ?," +
                " Director_idDirector = ? WHERE idMovie = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, MovieName);
        preparedStmt.setString(2, MovieYear);
        preparedStmt.setLong(3, Country_idCountry);
        preparedStmt.setLong(4, Type_idType);
        preparedStmt.setLong(5, Director_idDirector);
        preparedStmt.setLong(6, idMovie);

        preparedStmt.execute();
    }

}
