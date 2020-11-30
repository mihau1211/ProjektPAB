package DBElements;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Actor {
    Scanner scan = new Scanner(System.in);
    String name;
    String surname;
    String birthDate;
    String countryName;
    long countryID;
    long actorID;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public long getCountry() {
        return countryID;
    }

    public long getActorID() {
        return actorID;
    }

    public String getCountryName(){
        return countryName;
    }

    public Actor(String name, String surname, String birthdate, long countryID, String countryName, long actorID){
        this.name = name;
        this.surname = surname;
        this.birthDate = birthdate;
        this.countryID = countryID;
        this.countryName = countryName;
        this.actorID = actorID;
    }

    public Actor(){

    }

    public String toString(){
        return name+"/"+surname+"/"+birthDate+"/"+countryID+"/"+actorID;
    }

    public ArrayList<Actor> getActors(Connection conn){
        ArrayList<Actor> actors = new ArrayList<Actor>();

        String query = "SELECT * FROM Actor, Country WHERE Country_idCountry=idCountry ";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Actor actor = new Actor(rs.getString("ActorName"),rs.getString("ActorSurname"),rs.getString("ActorBirthDate"), rs.getLong("Country_idCountry"), rs.getString("CountryName"), rs.getLong("idActor"));
                actors.add(actor);
            }
            return actors;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void insertActor(Connection conn,String name, String surname, String birthDate, long country) throws SQLException {

        String query = " insert into Actor (ActorName, ActorSurname, ActorBirthDate, Country_idCountry)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, surname);
        preparedStmt.setString (3, birthDate);
        preparedStmt.setLong (4, country);

        preparedStmt.execute();
    }
    public void deleteActorByID(Connection conn, long id) throws SQLException {

        String query1 = "DELETE FROM Actor_Has_Movie WHERE Actor_idActor=?;";
        String query2 = "DELETE FROM Actor WHERE idActor=?;";

        PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
        preparedStmt1.setLong(1, id);
        preparedStmt1.execute();

        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setLong(1, id);
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
    public void updateActor(Connection conn, String name, String surname, String birthDate, long countryID, long actorID) throws SQLException{

        String query = "UPDATE Actor SET ActorName = ?, ActorSurname = ?, ActorBirthDate = ?, Country_idCountry = ? WHERE idActor = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, surname);
        preparedStmt.setString(3, birthDate);
        preparedStmt.setLong(4, countryID);
        preparedStmt.setLong(5, actorID);

        preparedStmt.execute();
    }
}
