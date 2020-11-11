package Window;

import Connection.ConnectToSql;
import DBElements.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {


    MenuBar mb = new MenuBar();
    Actor actor = new Actor();
    Director director=new Director();
    Movie movie=new Movie();
    Country country=new Country();
    DBElements.Type type= new DBElements.Type();
    ActorHasMovie actorHasMovie= new ActorHasMovie();


    long id,id2;
    String name;



    ConnectToSql connectToSql = new ConnectToSql();
    public MyFrame() {
        super("Baza filmowa");
        connectToSql.startConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);

        //dodanie listenerow
        for (int i=0; i<mb.menus.length; i++){
            mb.menus[i].addActionListener(this);
            for (int j=0; j<mb.items.length; j++){
                mb.menus[i].getItem(j).addActionListener(this);
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

        //actor
        if(source==mb.menus[0].getItem(0)){
            try {
                actor.insertActor(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[0].getItem(1)){
            try {
                actor.deleteActorByID(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[0].getItem(2)){
            try {
                actor.selectActor(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[0].getItem(3)){
            try {
                actor.updateActor(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    //director

        if(source==mb.menus[1].getItem(0)){
            try {
                director.insertDirector(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(1)){
            try {
                director.deleteDirectorByID(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(2)){
            try {
                director.selectDirector(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[1].getItem(3)){
            try {
                director.updateDirector(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    //movie
        if(source==mb.menus[2].getItem(0)){
            try {
                movie.insertMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[2].getItem(1)){
            try {
                movie.deleteMovieByID(connectToSql.conn);
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
                movie.updateMovie(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //country
        if(source==mb.menus[3].getItem(0)){
            try {
                country.insertCountry(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(1)){
            try {
                country.deleteCountryByID(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(2)){
            try {
                country.selectCountry(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[3].getItem(3)){
            try {
                country.updateCountry(connectToSql.conn,id,name);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



        //type
        if(source==mb.menus[4].getItem(0)){
            try {
                type.insertType(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[4].getItem(1)){
            try {
                type.deleteTypeByID(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[4].getItem(2)){
            try {
                type.selectType(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[4].getItem(3)){
            try {
                type.updateType(connectToSql.conn,id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



        //actorHMovie
        if(source==mb.menus[5].getItem(0)){
            try {
                actorHasMovie.insertActorHasMovie(connectToSql.conn,id,id2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[5].getItem(1)){
            try {
                actorHasMovie.deleteActorHasMovie(connectToSql.conn,id,id2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(source==mb.menus[5].getItem(2)){
            try {
                actorHasMovie.selectActorHasMovie(connectToSql.conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }










        //test-dla kazdego obiektu menu konsola wyswietla jego pozycje w tablicy
        for (int i=0; i<mb.menus.length; i++){
            for (int j=0; j<4; j++){
                if(source==mb.menus[i].getItem(j)){
                    System.out.println("I:  "+i+"\nJ:  "+j);
                }
            }
        }
    }
}
