package Window.ElementFrames.Actor;

import Connection.ConnectToSql;
import DBElements.Actor;
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

public class ActorDeleteFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Actor actor = new Actor();
    ArrayList<Actor> actors = new ArrayList<Actor>();

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"ID","Name","Surname","Birthdate","Country"};
    long id;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public ActorDeleteFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Delete Actor");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        actors = actor.getActors(connectToSql.conn);
        Object[][] rows = new Object[actors.size()][5];
        for (int i=0; i<actors.size(); i++){
            for (int j=0; j<5; j++){
                if (j==0)rows[i][j] = actors.get(i).getActorID();
                if (j==1)rows[i][j] = actors.get(i).getName();
                if (j==2)rows[i][j] = actors.get(i).getSurname();
                if (j==3)rows[i][j] = actors.get(i).getBirthDate();
                if (j==4)rows[i][j] = actors.get(i).getCountryName();
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
                actor.deleteActorByID(connectToSql.conn, id);
                setMessage("Actor has been removed.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }
    }
}
