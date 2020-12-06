package DBElements;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ActorHasMovie {
    long actorID;
    long movieID;

    public long getActorID() {
        return actorID;
    }

    public long getMovieID() {
        return movieID;
    }

    public ActorHasMovie(long actorID, long movieID) {
        this.actorID = actorID;
        this.movieID = movieID;
    }

    public ActorHasMovie() {

    }

    public ArrayList<ActorHasMovie> getAHMS(Connection conn){
        ArrayList<ActorHasMovie> ahms = new ArrayList<ActorHasMovie>();

        String query = "SELECT * FROM Actor_Has_Movie";

        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                ActorHasMovie ahm = new ActorHasMovie(rs.getLong("Actor_idActor"),rs.getLong("Movie_idMovie"));
                ahms.add(ahm);
            }
            return ahms;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void insertActorHasMovie(Connection conn, long actorID, long movieID) throws SQLException {

        String query = " insert into Actor_Has_Movie (Actor_idActor, Movie_idMovie)"
                + " values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, actorID);
        preparedStmt.setLong (2, movieID);

        preparedStmt.execute();
    }
    public void deleteActorHasMovie(Connection conn) throws SQLException {

        String query = "DELETE FROM Actor_Has_Movie WHERE Actor_idActor=? AND Movie_idMovie=?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, getActorID());
        preparedStmt.setLong (2, getMovieID());

        preparedStmt.execute();
    }
    public void selectActorHasMovie(Connection conn) throws SQLException {

        String query = "SELECT * FROM Actor_Has_Movie";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            long idActor = rs.getLong("Actor_idActor");
            String idMovie = rs.getString("Movie_idMovie");
            System.out.format("|%1$-5s|%2$-5s|\n", idActor, idMovie);
        }
    }

}
