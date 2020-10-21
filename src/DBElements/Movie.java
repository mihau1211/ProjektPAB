package DBElements;

import java.sql.*;

public class Movie {
    public void insertMovie(Connection conn, String movieName, String movieYear, int movieRating,
                         long Country_idCountry, long Type_idType, long Director_idDirector) throws SQLException {

        String query = " insert into Movie (movieName, movieYear, movieRating, Country_idCountry, Type_idType, " +
                "Director_idDirector)"
                + " values (?,?,?,?,?,?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, movieName);
        preparedStmt.setString (2, movieYear);
        preparedStmt.setInt   (3, movieRating);
        preparedStmt.setLong(4, Country_idCountry);
        preparedStmt.setLong(5, Type_idType);
        preparedStmt.setLong(6, Director_idDirector);

        preparedStmt.execute();
    }
    public void deleteMovieByID(Connection conn, long idMovie) throws SQLException {

        String query = "DELETE FROM Movie WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idMovie);

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
