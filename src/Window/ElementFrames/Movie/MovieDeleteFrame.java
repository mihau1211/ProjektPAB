package Window.ElementFrames.Movie;

import Connection.ConnectToSql;
import DBElements.Actor;
import DBElements.Movie;
import Window.MainFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class MovieDeleteFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Movie movie = new Movie();
    ArrayList<Movie> movies = new ArrayList<Movie>();

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"ID","Name","Year","Rating","Director","Type","Country"};
    long id;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public MovieDeleteFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Delete Movie");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() > -1) {
                    id= Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
                    System.out.println(id);
                }
            }
        });

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40,50,400, 300);
        pane.setVisible(true);
        add(pane);

        add(okButton);

        setSize(500,500);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source==okButton){
            try {
                movie.deleteMovieByID(connectToSql.conn, id);
                setMessage("Movie has been removed.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }
    }
}
