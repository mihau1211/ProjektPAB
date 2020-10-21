import DBElements.*;
import Connection.*;

import java.lang.reflect.AccessibleObject;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectToSql connectToSql;
        connectToSql=new ConnectToSql();
        connectToSql.startConnection();

        Actor actor = new Actor();
        ActorHasMovie actorHasMovie = new ActorHasMovie();
        Country country = new Country();
        Director director = new Director();
        Movie movie = new Movie();
        Type type = new Type();
        
//        insert.addCountry(connectToSql.conn, "POLAND");
//        insert.addType(connectToSql.conn, "Action");
//        insert.addDirector(connectToSql.conn, "TMP Director", "1982-09-12", 1);
//        insert.addMovie(connectToSql.conn, "TMP movie", "2020-01-01", 8, 1, 1, 1);
//        insert.addActor(connectToSql.conn, "TMP Actor", "1948-04-12", 1);
//        insert.addActorHasMovie(connectToSql.conn, 2,2);

    }
}
