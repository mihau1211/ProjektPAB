package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Actor {
    Scanner scan = new Scanner(System.in);
    String name;
    String surname;
    String birthDate;
    long countryID;
    long actorID;

    public String getName() {
        System.out.println("Podaj imie:");
        return name = scan.nextLine();
    }

    public String getSurname() {
        System.out.println("Podaj nazwisko:");
        return surname = scan.nextLine();
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
        System.out.println("Podaj ID:");
        return actorID = scan.nextLong();
    }

    public void insertActor(Connection conn) throws SQLException {

        String query = " insert into Actor (ActorName, ActorSurname, ActorBirthDate, Country_idCountry)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());
        preparedStmt.setString (2, getSurname());
        preparedStmt.setString (3, getBirthDate());
        preparedStmt.setLong (4, getCountry());

        preparedStmt.execute();
    }
    public void deleteActorByID(Connection conn) throws SQLException {

        actorID=getActorID();

        String query1 = "DELETE FROM Actor_Has_Movie WHERE Actor_idActor=?;";
        String query2 = "DELETE FROM Actor WHERE idActor=?;";

        PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
        preparedStmt1.setLong(1, actorID);
        preparedStmt1.execute();

        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setLong (1, actorID);
        preparedStmt2.execute();
    }
    public void deleteActorByName(Connection conn, String ActorName) throws SQLException{
        String query = "DELETE FROM actor WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, ActorName);

        preparedStmt.execute();
    }
    public void selectActor(Connection conn) throws SQLException {

        String query = "SELECT idActor, ActorName, ActorSurname, ActorBirthDate, CountryName FROM Actor, Country WHERE Country_idCountry=idCountry";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println("==========");
        System.out.println("||ACTORS||");
        System.out.println("==========");

        System.out.println("| ID  |       NAME         |       SURNAME         |    YEAR   |   COUNTRY   |");

        while (rs.next()){
            long id = rs.getLong("idActor");
            String name = rs.getString("ActorName");
            String surname = rs.getString("ActorSurname");
            String year = rs.getString("ActorBirthDate");
            String country = rs.getString("CountryName");
            System.out.format("|%1$-5s|%2$-20s|%3$-23s|%4$-11s|%5$-13s|\n", id, name, surname, year, country);
        }
    }
    public void updateActor(Connection conn) throws SQLException{

        actorID=getActorID();
        String query = "UPDATE Actor SET ActorName = ?, ActorSurname = ?, ActorBirthDate = ?, Country_idCountry = ? WHERE idActor = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, getName());
        preparedStmt.setString(1, getSurname());
        preparedStmt.setString(3, getBirthDate());
        preparedStmt.setLong(4, getCountry());
        preparedStmt.setLong(5, actorID);

        preparedStmt.execute();
    }
}
