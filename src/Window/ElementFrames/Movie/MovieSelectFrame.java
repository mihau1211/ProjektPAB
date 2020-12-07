package Window.ElementFrames.Movie;

import Connection.ConnectToSql;
import DBElements.Actor;
import DBElements.Movie;
import Window.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MovieSelectFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Movie movie = new Movie();

    DefaultTableModel model;
    ArrayList<Movie> movies = new ArrayList<Movie>();

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"ID","Name","Year","Rating","Director","Type","Country"};

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void getMoviesDefault(){
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        add(okButton);
        movies = movie.getMovies(connectToSql.conn);
        Object[][] rows = new Object[movies.size()][7];
        for (int i=0; i<movies.size(); i++){
            for (int j=0; j<7; j++){
                if (j==0)rows[i][j] = movies.get(i).getMovieID();
                if (j==1)rows[i][j] = movies.get(i).getName();
                if (j==2)rows[i][j] = movies.get(i).getYear();
                if (j==3)rows[i][j] = movies.get(i).getRating();
                if (j==4)rows[i][j] = movies.get(i).getDirectorName();
                if (j==5)rows[i][j] = movies.get(i).getTypeName();
                if (j==6)rows[i][j] = movies.get(i).getCountryName();
            }
        }

        table = new JTable(rows, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40,50,400, 300);
        pane.setVisible(true);
        add(pane);
    }

    public MovieSelectFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Select Movies");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        add(okButton);
        movies = movie.getMovies(connectToSql.conn);
        Object[][] rows = new Object[movies.size()][7];
        for (int i=0; i<movies.size(); i++){
            for (int j=0; j<7; j++){
                if (j==0)rows[i][j] = movies.get(i).getMovieID();
                if (j==1)rows[i][j] = movies.get(i).getName();
                if (j==2)rows[i][j] = movies.get(i).getYear();
                if (j==3)rows[i][j] = movies.get(i).getRating();
                if (j==4)rows[i][j] = movies.get(i).getDirectorName();
                if (j==5)rows[i][j] = movies.get(i).getTypeName();
                if (j==6)rows[i][j] = movies.get(i).getCountryName();
            }
        }

        table = new JTable(rows, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40,50,400, 300);
        pane.setVisible(true);
        add(pane);

        setSize(500,500);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source==okButton){
            setMessage("Movie has been selected.");
            dispose();
        }
    }
}
