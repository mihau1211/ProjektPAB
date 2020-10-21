import DBElements.*;
import Connection.*;

import java.lang.reflect.AccessibleObject;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        ConnectToSql connectToSql;
        connectToSql=new ConnectToSql();
        connectToSql.startConnection();

        Actor actor = new Actor();
        ActorHasMovie actorHasMovie = new ActorHasMovie();
        Country country = new Country();
        Director director = new Director();
        Movie movie = new Movie();
        Type type = new Type();


//        country.insertCountry(connectToSql.conn, "POLAND");
//        type.insertType(connectToSql.conn, "Action");
//        director.insertDirector(connectToSql.conn, "TMP Director", "1982-09-12", 1);
//        movie.insertMovie(connectToSql.conn, "TMP movie", "2020-01-01", 8, 1, 1, 1);
//        actor.insertActor(connectToSql.conn, "TMP Actor", "1948-04-12", 1);
//        actorHasMovie.insertActorHasMovie(connectToSql.conn, 1, 1);

//        actor.updateActor(connectToSql.conn, 1, "TMP actor", "1982-09-12", 1);
//        actor.selectActor(connectToSql.conn);
//        actorHasMovie.selectActosHasMovie(connectToSql.conn);
//        country.selectCountry(connectToSql.conn);
//        director.selectDirector(connectToSql.conn);
//        movie.selectMovie(connectToSql.conn);
//        type.selectType(connectToSql.conn);
    }
}
