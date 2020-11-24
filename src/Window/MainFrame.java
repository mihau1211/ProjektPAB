package Window;

import Connection.ConnectToSql;
import DBElements.*;
import Window.ElementFrames.ActorAddFrame;
import Window.ElementFrames.ActorDeleteFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {


    MenuBar mb = new MenuBar();
    Actor actor = new Actor();
    Director director=new Director();
    Movie movie=new Movie();
    Country country=new Country();
    DBElements.Type type= new DBElements.Type();
    ActorHasMovie actorHasMovie= new ActorHasMovie();

    JDialog jDialog;

    ActorAddFrame actorAddFrame;
    ActorDeleteFrame actorDeleteFrame;

    ConnectToSql connectToSql = new ConnectToSql();
    public MainFrame() {
        super("Baza filmowa");
        connectToSql.startConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);

        //dodanie listenerow
        for (int i=0; i<mb.menus.length; i++){
            mb.menus[i].addActionListener(this);
            for (int j=0; j<mb.tables.length; j++){
                if (i!=3 || j!=5) {
                    mb.menus[i].getItem(j).addActionListener(this);
                }
            }
        }

        setJMenuBar(mb.mb);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

////warunki dzialania listenerow

        //insert
        if(source==mb.menus[0].getItem(0)){
            actorAddFrame = new ActorAddFrame();
        }
        if(source==mb.menus[0].getItem(1)){
            System.out.println("xasdasdasdas");
        }
        if(source==mb.menus[0].getItem(2)){
            try {
                movie.insertMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[0].getItem(3)){
            try {
                country.insertCountry(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(source==mb.menus[0].getItem(4)){
            try {
                type.insertType(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[0].getItem(5)){
            try {
                actorHasMovie.insertActorHasMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //delete
        if(source==mb.menus[1].getItem(0)){
            actorDeleteFrame = new ActorDeleteFrame();
        }
        if(source==mb.menus[1].getItem(1)){
            try {
                director.deleteDirectorByID(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(2)){
            try {
                movie.deleteMovieByID(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(3)){
            try {
                country.deleteCountryByID(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(4)){
            try {
                type.deleteTypeByID(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(5)){
            try {
                actorHasMovie.deleteActorHasMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //select
        if(source==mb.menus[2].getItem(0)){
            try {
                actor.selectActor(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[2].getItem(1)){
            try {
                director.selectDirector(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[2].getItem(2)){
            try {
                movie.selectMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[2].getItem(3)){
            try {
                country.selectCountry(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[2].getItem(4)){
            try {
                type.selectType(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[2].getItem(5)){
            try {
                actorHasMovie.selectActorHasMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //update
        if(source==mb.menus[3].getItem(0)){
            try {
                actor.updateActor(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(1)){
            try {
                director.updateDirector(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(2)){
            try {
                movie.updateMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(3)){
            try {
                country.updateCountry(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(4)){
            try {
                type.updateType(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }










        //test-dla kazdego obiektu menu konsola wyswietla jego pozycje w tablicy
//        for (int i=0; i<mb.menus.length; i++){
//            for (int j=0; j<4; j++){
//                if(source==mb.menus[i].getItem(j)){
//                    System.out.println("I:  "+i+"\nJ:  "+j);
//                }
//            }
//        }
    }
}
