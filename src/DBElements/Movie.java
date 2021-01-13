package DBElements;

import java.sql.*;
import java.util.ArrayList;
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
    String countryName;
    String directorName;
    String typeName;

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }

    public long getTypeID() {
        return typeID;
    }

    public long getDirectorID() {
        return directorID;
    }

    public long getCountry() {
        return countryID;
    }

    public long getMovieID() {
        return movieID;
    }

    public String getCountryName(){return countryName;}

    public String getDirectorName() {
        return directorName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString(){
        return movieID + " / " + name + " / " + year + " / " + rating + " / " + typeName + " / " + directorName + " / " + countryName;
    }

    public Movie(String name, String year, int rating, long typeID, long directorID, long countryID, long movieID, String countryName, String directorName, String typeName) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.typeID = typeID;
        this.directorID = directorID;
        this.countryID = countryID;
        this.movieID = movieID;
        this.countryName = countryName;
        this.directorName = directorName;
        this.typeName = typeName;
    }

    public Movie(){

    }

    public ArrayList<Movie> getMovies(Connection conn){
        ArrayList<Movie> movies = new ArrayList<Movie>();

//        String query = "SELECT * FROM Movie, Country, Type, Director WHERE Country_idCountry=idCountry OR Type_idType=idType OR Director_idDirector=idDirector";
        String query = "select * from Movie join Country ON Country_idCountry=idCountry join Type ON Type_idType=idType join Director ON Director_idDirector=idDirector;";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Movie movie = new Movie(rs.getString("MovieName"), rs.getString("MovieYear"), rs.getInt("MovieRating"),
                        rs.getLong("Type_idType"), rs.getLong("Director_idDirector"), rs.getLong("Country_idCountry"),
                        rs.getLong("idMovie"), rs.getString("CountryName"), rs.getString("DirectorName"),
                        rs.getString("TypeName"));
                movies.add(movie);
            }
            return movies;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void insertMovie(Connection conn, String name, String year, int rating, long countryID, long typeID, long directorID) throws SQLException {

        String query = " insert into Movie (movieName, movieYear, movieRating, Country_idCountry, Type_idType, " +
                "Director_idDirector)"
                + " values (?,?,?,?,?,?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, year);
        preparedStmt.setInt   (3, rating);
        preparedStmt.setLong(4, countryID);
        preparedStmt.setLong(5, typeID);
        preparedStmt.setLong(6, directorID);

        preparedStmt.execute();
    }
    public void deleteMovieByID(Connection conn, long id) throws SQLException {

        movieID=getMovieID();

        String query1 = "DELETE FROM Actor_Has_Movie WHERE Movie_idMovie=?;";
        String query2 = "DELETE FROM Movie WHERE idMovie=?;";

        PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
        preparedStmt1.setLong(1, id);
        preparedStmt1.execute();

        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setLong (1, id);
        preparedStmt2.execute();
    }
    public void deleteMovieByName(Connection conn, String MovieName) throws SQLException{
        String query = "DELETE FROM Movie WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, MovieName);

        preparedStmt.execute();
    }
    public void selectMovie(Connection conn) throws SQLException {

        String query = "SELECT idMovie, MovieName, MovieYear, MovieRating, TypeName FROM Movie, Type WHERE Type_idType=idType;";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("=========");
        System.out.println("||MOVIE||");
        System.out.println("=========");
        System.out.println("| ID  |RATING|    NAME   |    YEAR    |     TYPE    |");

        while (rs.next()) {
            long id = rs.getLong("idMovie");
            String name = rs.getString("MovieName");
            String year = rs.getString("MovieYear");
            int rating = rs.getInt("MovieRating");
            String type = rs.getString("TypeName");
            System.out.format("|%1$-5s|%2$-6s|%3$-11s|%4$-12s|%5$-13s|\n",id,  rating, name, year, type);
        }
    }
    public void updateMovie(Connection conn, String name, String year, int rating, long countryID, long typeID, long directorID, long movieID) throws SQLException{

        String query = "UPDATE Movie SET MovieName = ?, MovieYear = ?, MovieRating = ?, Country_idCountry = ?," +
                " Type_idType = ?, Director_idDirector = ? WHERE idMovie = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, year);
        preparedStmt.setInt   (3, rating);
        preparedStmt.setLong(4, countryID);
        preparedStmt.setLong(5, typeID);
        preparedStmt.setLong(6, directorID);
        preparedStmt.setLong(7, movieID);

        preparedStmt.execute();
    }

}
