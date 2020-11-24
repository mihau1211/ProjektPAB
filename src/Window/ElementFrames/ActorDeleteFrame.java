package Window.ElementFrames;

import Connection.ConnectToSql;
import DBElements.Actor;
import Window.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.Flow;

public class ActorDeleteFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Actor actor = new Actor();

    JDialog errorDialog = new JDialog(this);
    JDialog confirmDialog = new JDialog(this);

    JTextField idField = new JTextField();

    JButton okButton = new JButton("OK");
    JButton errorButton = new JButton("OK");
    JButton confirmButton = new JButton("OK");

    JLabel title = new JLabel("To remove actor type ID in box below.");
    JLabel idLabel = new JLabel("ID:");

    long id;

    public long getID() {
        return Long.parseLong(idField.getText());
    }

    public ActorDeleteFrame() {
        setLayout(null);

        errorDialog.setBounds(350,250, 200, 100);
        errorDialog.setLayout(new FlowLayout());
        confirmDialog.setBounds(350,250, 230, 100);
        confirmDialog.setLayout(new FlowLayout());

        idField.setBounds(150, 50, 300, 30);

        okButton.setBounds(230, 100, 120, 40);
        okButton.addActionListener(this);
        errorButton.addActionListener(this);
        confirmButton.addActionListener(this);

        title.setBounds(50,20,400,30);
        idLabel.setBounds(50,50,70,30);

        add(idField);

        add(okButton);

        confirmDialog.add(new JLabel("You delete actor from database!"));
        confirmDialog.add(confirmButton);
        errorDialog.add(new JLabel("ERROR: WRONG DATA"));
        errorDialog.add(errorButton);

        add(title);
        add(idLabel);

        setSize(500,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        id = getID();

        if (source==okButton){
            connectToSql.startConnection();
            try {
                actor.deleteActorByID(connectToSql.conn, id);
                confirmDialog.setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                errorDialog.setVisible(true);
            }
        }

        if (source==errorButton){
            dispose();
        }

        if (source==confirmButton){
            dispose();
        }
    }
}
