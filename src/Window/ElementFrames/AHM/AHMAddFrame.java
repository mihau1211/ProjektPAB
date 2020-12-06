package Window.ElementFrames.AHM;

import Connection.ConnectToSql;
import DBElements.Actor;
import DBElements.ActorHasMovie;
import DBElements.Movie;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AHMAddFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Actor actor = new Actor();
    ActorHasMovie ahm = new ActorHasMovie();
    Movie movie = new Movie();
    ArrayList<Actor> actors;
    ArrayList<Movie> movies;

    JButton okButton = new JButton("OK");

    JTable table1;
    JTable table2;

    String message;
    String[] columnNamesActor = {"ID","Name","Surname","Birthdate","Country"};
    String[] columnNamesMovie = {"ID","Name","Year","Rating","Director","Type","Country"};
    long actorID;
    long movieID;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }


    public AHMAddFrame(){
        setLayout(null);
        setModal(true);
        setTitle("Add AHM");
        connectToSql.startConnection();
        okButton.setBounds(450, 360, 120, 40);
        okButton.addActionListener(this);
        add(okButton);


        //// ACTOR

        actors = actor.getActors(connectToSql.conn);
        Object[][] rowsActor = new Object[actors.size()][5];
        for (int i=0; i<actors.size(); i++){
            for (int j=0; j<5; j++){
                if (j==0)rowsActor[i][j] = actors.get(i).getActorID();
                if (j==1)rowsActor[i][j] = actors.get(i).getName();
                if (j==2)rowsActor[i][j] = actors.get(i).getSurname();
                if (j==3)rowsActor[i][j] = actors.get(i).getBirthDate();
                if (j==4)rowsActor[i][j] = actors.get(i).getCountryName();
            }
        }

        table1 = new JTable(rowsActor, columnNamesActor);
        table1.setPreferredScrollableViewportSize(new Dimension(500,50));
        table1.setFillsViewportHeight(true);

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (table1.getSelectedRow() > -1) {
                    actorID= Long.parseLong(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    System.out.println(actorID);
                }
            }
        });

        JScrollPane pane1 = new JScrollPane(table1);
        pane1.setBounds(40,50,400, 300);
        pane1.setVisible(true);
        add(pane1);

        //// MOVIE

        movies = movie.getMovies(connectToSql.conn);
        Object[][] rowsMovie = new Object[movies.size()][7];
        for (int i=0; i<movies.size(); i++){
            for (int j=0; j<7; j++){
                if (j==0)rowsMovie[i][j] = movies.get(i).getMovieID();
                if (j==1)rowsMovie[i][j] = movies.get(i).getName();
                if (j==2)rowsMovie[i][j] = movies.get(i).getYear();
                if (j==3)rowsMovie[i][j] = movies.get(i).getRating();
                if (j==4)rowsMovie[i][j] = movies.get(i).getDirectorName();
                if (j==5)rowsMovie[i][j] = movies.get(i).getTypeName();
                if (j==6)rowsMovie[i][j] = movies.get(i).getCountryName();
            }
        }

        table2 = new JTable(rowsMovie, columnNamesMovie);
        table2.setPreferredScrollableViewportSize(new Dimension(500,50));
        table2.setFillsViewportHeight(true);

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (table2.getSelectedRow() > -1) {
                    movieID= Long.parseLong(table2.getValueAt(table2.getSelectedRow(), 0).toString());
                    System.out.println(movieID);
                }
            }
        });

        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setBounds(540,50,400, 300);
        pane2.setVisible(true);
        add(pane2);

        setSize(1000,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source==okButton){
            try {
                ahm.insertActorHasMovie(connectToSql.conn, actorID, movieID);
                setMessage("Actor and movie has been connected.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }
    }
}
