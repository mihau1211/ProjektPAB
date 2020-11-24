package Window.ElementFrames;

import Connection.ConnectToSql;
import DBElements.Actor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ActorAddFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Actor actor = new Actor();

    JDialog errorDialog = new JDialog(this);
    JDialog confirmDialog = new JDialog(this);

    JTextField nameField = new JTextField();
    JTextField surnameField = new JTextField();
    JTextField birthdateField = new JTextField();
    JTextField countryField = new JTextField();

    JButton okButton = new JButton("OK");
    JButton errorButton = new JButton("OK");
    JButton confirmButton = new JButton("OK");

    JLabel title = new JLabel("To add actor type data in boxes below.");
    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel birthdateLabel = new JLabel("Birthdate:");
    JLabel countryLabel = new JLabel("Country:");

    String name;
    String surname;
    String birthdate;
    long country;

    @Override
    public String getName() {
        return nameField.getText();
    }

    public String getSurname() {
        return surnameField.getText();
    }

    public String getBirthdate() {
        return birthdateField.getText();
    }

    public long getCountry() {
        return Long.parseLong(countryField.getText());
    }

    public ActorAddFrame() {
        setLayout(null);

        errorDialog.setBounds(350,250, 200, 100);
        errorDialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        confirmDialog.setBounds(350,250, 230, 100);
        confirmDialog.setLayout(new FlowLayout(FlowLayout.CENTER));

        nameField.setBounds(150, 50, 300, 30);
        surnameField.setBounds(150, 100, 300, 30);
        birthdateField.setBounds(150, 150, 300, 30);
        countryField.setBounds(150, 200, 300, 30);

        okButton.setBounds(230, 250, 120, 40);
        okButton.addActionListener(this);
        errorButton.addActionListener(this);
        confirmButton.addActionListener(this);

        title.setBounds(50,20,400,30);
        nameLabel.setBounds(50,50,70,30);
        surnameLabel.setBounds(50,100,70,30);
        birthdateLabel.setBounds(50,150,70,30);
        countryLabel.setBounds(50,200,70,30);

        add(nameField);
        add(surnameField);
        add(birthdateField);
        add(countryField);

        add(okButton);

        confirmDialog.add(new JLabel("You add new actor into database!"));
        confirmDialog.add(confirmButton);
        errorDialog.add(new JLabel("ERROR: WRONG DATA"));
        errorDialog.add(errorButton);

        add(title);
        add(nameLabel);
        add(surnameLabel);
        add(birthdateLabel);
        add(countryLabel);

        setSize(500,350);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        name = getName();
        surname = getSurname();
        birthdate = getBirthdate();
        country = getCountry();

        if (source==okButton){
            connectToSql.startConnection();
            try {
                actor.insertActor(connectToSql.conn, name, surname, birthdate, country);
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
