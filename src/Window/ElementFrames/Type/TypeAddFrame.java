package Window.ElementFrames.Type;

import Connection.ConnectToSql;
import DBElements.Actor;
import DBElements.Country;
import DBElements.Director;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeAddFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    DBElements.Type type = new DBElements.Type();

    JTextField nameField = new JTextField();

    JButton okButton = new JButton("OK");

    JLabel title = new JLabel("To add type type data in box below.");
    JLabel nameLabel = new JLabel("Name:");

    String name;

    String message;

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String getName() {
        return nameField.getText();
    }

    public String getMessage(){
        return message;
    }

    public TypeAddFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Add Type");
        connectToSql.startConnection();

        nameField.setBounds(150, 50, 300, 30);

        okButton.setBounds(230, 100, 120, 40);
        okButton.addActionListener(this);

        title.setBounds(50,20,400,30);
        nameLabel.setBounds(50,50,70,30);

        add(nameField);

        add(okButton);

        add(title);
        add(nameLabel);

        setSize(500,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        name = getName();

        if (source==okButton){
            try {
                type.insertType(connectToSql.conn, name);
                setMessage("Type has been added.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }
    }
}
