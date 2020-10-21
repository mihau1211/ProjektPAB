package DBElements;

import java.sql.*;

public class ActorHasMovie {
    public void insertActorHasMovie(Connection conn, long Actor_idActor, long Movie_idMovie) throws SQLException {

        String query = " insert into Actor_Has_Movie (Actor_idActor, Movie_idMovie)"
                + " values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, Actor_idActor);
        preparedStmt.setLong (2, Movie_idMovie);

        preparedStmt.execute();
    }
    public void deleteAHM(Connection conn, long idActor, long idMovie) throws SQLException {

        String query = "DELETE FROM Actor_Has_Movie WHERE Actor_idActor=? AND Movie_idMovie=?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idActor);
        preparedStmt.setLong (2, idMovie);

        preparedStmt.execute();
    }
    public void selectActosHasMovie(Connection conn) throws SQLException {

        String query = "SELECT * FROM Actor_Has_Movie";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            long idActor = rs.getLong("Actor_idActor");
            String idMovie = rs.getString("Movie_idMovie");
            System.out.format("%s, %s \n", idActor, idMovie);
        }
    }

}
