package Window.ElementFrames.Director;

import Connection.ConnectToSql;
import DBElements.Director;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class DirectorSelectFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Director director = new Director();

    DefaultTableModel model;
    ArrayList<Director> directors;

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"ID","Name","Surname","Birthdate","Country"};

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public DirectorSelectFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Select Actors");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        add(okButton);
        directors = director.getDirectors(connectToSql.conn);
        Object[][] rows = new Object[directors.size()][5];
        for (int i=0; i<directors.size(); i++){
            for (int j=0; j<5; j++){
                if (j==0)rows[i][j] = directors.get(i).getDirectorID();
                if (j==1)rows[i][j] = directors.get(i).getName();
                if (j==2)rows[i][j] = directors.get(i).getSurname();
                if (j==3)rows[i][j] = directors.get(i).getBirthDate();
                if (j==4)rows[i][j] = directors.get(i).getCountryName();
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
            setMessage("Directors has been selected.");
            dispose();
        }
    }
}
