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


        boolean end=true;
        int switcher;
        while(end){
            System.out.println("================================================================================");
            System.out.println("| 1. Dodaj film");
            System.out.println("| 2. Dodaj aktora");
            System.out.println("| 3. Pokaz filmy");
            System.out.println("| 4. Pokaz aktorow");
            System.out.println("| 5. Pokaz rezyserow");
            System.out.println("| 6. Usun film");
            System.out.println("| 7. Usun aktora");
            System.out.println("| 8. Wyjdz");
            switcher = scan.nextInt();
            switch(switcher){
                case 1:
                    movie.insertMovie(connectToSql.conn);
                    break;
                case 2:
                    actor.insertActor(connectToSql.conn);
                    break;
                case 3:
                    movie.selectMovie(connectToSql.conn);
                    break;
                case 4:
                    System.out.println("-----------------------------------------------\nActor Name----------Actor Birth Date\n");
                    actor.selectActor(connectToSql.conn);
                    break;
                case 5:
                    director.selectDirector(connectToSql.conn);
                    break;
                case 6:
                    movie.deleteMovieByID(connectToSql.conn);
                    break;
                case 7:
                    actor.deleteActorByID(connectToSql.conn);
                    break;
                case 8:
                    end=false;
                    System.out.println("Bye!!!!!!!!!");
                    break;

            }
        }
    }
}
