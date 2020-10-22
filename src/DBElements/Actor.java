package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Actor {
    Scanner scan = new Scanner(System.in);
    String name;
    String birthDate;
    long countryID;
    long actorID;

    public String getName() {
        System.out.println("Podaj imie i nazwisko:");
        return name = scan.nextLine();
    }

    public String getBirthDate() {
        System.out.println("Podaj date urodzenia:");
        return birthDate = scan.nextLine();
    }

    public long getCountry() {
        System.out.println("Podaj kraj pochodzenia:");
        return countryID = scan.nextLong();
    }

    public long getActorID() {
        return actorID = scan.nextLong();
    }

    public void insertActor(Connection conn) throws SQLException {

        String query = " insert into Actor (ActorName, ActorBirthDate, Country_idCountry)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());
        preparedStmt.setString (2, getBirthDate());
        preparedStmt.setLong (3, getCountry());

        preparedStmt.execute();
    }
    public void deleteActorByID(Connection conn) throws SQLException {

        String query = "DELETE FROM actor WHERE idActor=?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, getActorID());

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
            System.out.format("| %s | ---------- | %s | ---------- | %s |\n", id, name, year);
        }
    }
    public void updateActor(Connection conn, long idActor) throws SQLException{

        String query = "UPDATE Actor SET ActorName = ?, ActorBirthDate = ?, Country_idCountry = ? WHERE idActor = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, getName());
        preparedStmt.setString(2, getBirthDate());
        preparedStmt.setLong(3, getCountry());
        preparedStmt.setLong(4, idActor);

        preparedStmt.execute();
    }
}
