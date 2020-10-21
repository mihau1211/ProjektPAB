package DBElements;

import java.sql.*;

public class Actor {
    public void insertActor(Connection conn, String ActorName, String ActorBirthDate,
                         long Country_idCountry) throws SQLException {

        String query = " insert into Actor (ActorName, ActorBirthDate, Country_idCountry)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, ActorName);
        preparedStmt.setString (2, ActorBirthDate);
        preparedStmt.setLong (3, Country_idCountry);

        preparedStmt.execute();
    }
    public void deleteActorByID(Connection conn, long idActor) throws SQLException {

        String query = "DELETE FROM actor WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idActor);

        preparedStmt.execute();
    }
    public void deleteActorByName(Connection conn, String ActorName) throws SQLException{
        String query = "DELETE FROM actor WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, ActorName);

        preparedStmt.execute();
    }
    public void selectActor(Connection conn) throws SQLException {

        String query = "SELECT * FROM Actor";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            long id = rs.getLong("idActor");
            String name = rs.getString("ActorName");
            String year = rs.getString("ActorBirthDate");
            System.out.format("-----------------------------------------------\nActor Name----------Actor Birth Date\n%s ----------   %s\n", name, year);
        }
    }
}
