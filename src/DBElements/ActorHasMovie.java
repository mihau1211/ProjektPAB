package DBElements;

import java.sql.*;
import java.util.Scanner;

public class ActorHasMovie {
    Scanner scan = new Scanner(System.in);
    long actorID;
    long movieID;
    long ahmID;

    public long getActorID() {
        return actorID = scan.nextLong();
    }

    public long getMovieID() {
        return movieID = scan.nextLong();
    }

    public long getAhmID() {
        return ahmID = scan.nextLong();
    }

    public void insertActorHasMovie(Connection conn, long Actor_idActor, long Movie_idMovie) throws SQLException {

        String query = " insert into Actor_Has_Movie (Actor_idActor, Movie_idMovie)"
                + " values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, Actor_idActor);
        preparedStmt.setLong (2, Movie_idMovie);

        preparedStmt.execute();
    }
    public void deleteActorHasMovie(Connection conn, long idActor, long idMovie) throws SQLException {

        String query = "DELETE FROM Actor_Has_Movie WHERE Actor_idActor=? AND Movie_idMovie=?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idActor);
        preparedStmt.setLong (2, idMovie);

        preparedStmt.execute();
    }
    public void selectActorHasMovie(Connection conn) throws SQLException {

        String query = "SELECT * FROM Actor_Has_Movie";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("========");
        System.out.println("||ACTOR_HAS_MOVIE||");
        System.out.println("========");
        System.out.println(" |ID_A| |ID_M|");

        while (rs.next()){
            long idActor = rs.getLong("Actor_idActor");
            String idMovie = rs.getString("Movie_idMovie");
            System.out.format("|%1$-5s|%2$-5s|\n", idActor, idMovie);
        }
    }

}
