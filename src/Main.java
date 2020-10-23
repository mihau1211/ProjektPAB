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
            System.out.println("| 8. Zmodyfikuj film");
            System.out.println("| 9. Zmodyfikuj aktora");
            System.out.println("| 0. Wyjdz");
            System.out.println("================================================================================");

            long numberID;
            switcher = scan.nextInt();
            switch(switcher){
                case 1:
                    movie.insertMovie(connectToSql.conn);
                    break;
                case 2:
                    actor.insertActor(connectToSql.conn);
                    break;
                case 3:
                    System.out.printf("| ID| ---------- | RATING | ---------- | MOVIE TITLE | ---------- | RELEASE DATE |\n");
                    movie.selectMovie(connectToSql.conn);
                    break;
                case 4:
                    System.out.printf("| ID| ---------- | ACTOR NAME | ---------- | ACTOR BIRTH DATE |\n");
                    actor.selectActor(connectToSql.conn);
                    break;
                case 5:
                    System.out.printf("| ID| ---------- | DIRECTOR NAME | ---------- | DIRECTOR BIRTH DATE |\n");
                    director.selectDirector(connectToSql.conn);
                    break;
                case 6:
                    System.out.println("Podaj ID filmu do usuniecia:");
                    movie.deleteMovieByID(connectToSql.conn);
                    break;
                case 7:
                    System.out.println("Podaj ID aktora do usuniecia:");
                    actor.deleteActorByID(connectToSql.conn);
                    break;
                case 8:
                    System.out.println("Podaj id filmu ktory chcesz zmodyfikowac:");
                    numberID = scan.nextLong();
                    movie.updateMovie(connectToSql.conn, numberID);
                    break;
                case 9:
                    System.out.println("Podaj id aktora ktorego chcesz zmodyfikowac:");
                    numberID = scan.nextLong();
                    actor.updateActor(connectToSql.conn, numberID);
                    break;
                case 0:
                    end=false;
                    System.out.println("Bye!!!!!!!!!");
                    break;

            }
        }
    }
}
