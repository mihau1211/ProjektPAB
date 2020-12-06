package Window.ElementFrames.AHM;

import Connection.ConnectToSql;
import DBElements.ActorHasMovie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AHMSelectFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    ActorHasMovie ahm = new ActorHasMovie();

    ArrayList<ActorHasMovie> ahms;

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"Movie ID","Actor ID"};

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public AHMSelectFrame(){
        setLayout(null);
        setModal(true);
        setTitle("Select Types");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        add(okButton);

        ahms = ahm.getAHMS(connectToSql.conn);
        Object[][] rows = new Object[ahms.size()][2];
        for (int i=0; i<ahms.size(); i++){
            for (int j=0; j<2; j++){
                if (j==0)rows[i][j] = ahms.get(i).getActorID();
                if (j==1)rows[i][j] = ahms.get(i).getMovieID();
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
            setMessage("Actor has movie has been selected.");
            dispose();
        }
    }
}
