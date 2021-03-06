package Window.ElementFrames.Director;

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

public class DirectorAddFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Director director = new Director();
    Country country = new Country();

    ArrayList<Country> countries;

    JComboBox countryComboBox;

    JTextField nameField = new JTextField();
    JTextField surnameField = new JTextField();
    JTextField birthdateField = new JTextField();

    JButton okButton = new JButton("OK");

    JLabel title = new JLabel("To add director type data in boxes below.");
    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel birthdateLabel = new JLabel("Birthdate:");
    JLabel countryLabel = new JLabel("Country:");

    String comboCountryNames[];
    String name;
    String surname;
    String birthdate;
    long countryID = 1;

    String message;

    public void setMessage(String message){
        this.message = message;
    }

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

    public String getMessage(){
        return message;
    }

    public DirectorAddFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Add Director");
        connectToSql.startConnection();



        countries = country.getCountries(connectToSql.conn);

        comboCountryNames = new String[countries.size()];
        for (int i=0; i<countries.size(); i++){
            comboCountryNames[i] = countries.get(i).getName();
        }

        countryComboBox = new JComboBox(comboCountryNames);
        countryComboBox.setBounds(150, 200, 300, 30);
        countryComboBox.addActionListener(this);

        nameField.setBounds(150, 50, 300, 30);
        surnameField.setBounds(150, 100, 300, 30);
        birthdateField.setBounds(150, 150, 300, 30);

        okButton.setBounds(230, 250, 120, 40);
        okButton.addActionListener(this);

        title.setBounds(50,20,400,30);
        nameLabel.setBounds(50,50,70,30);
        surnameLabel.setBounds(50,100,70,30);
        birthdateLabel.setBounds(50,150,70,30);
        countryLabel.setBounds(50,200,70,30);

        add(nameField);
        add(surnameField);
        add(birthdateField);

        add(countryComboBox);

        add(okButton);

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

        if (source==okButton){
            try {
                director.insertDirector(connectToSql.conn, name, surname, birthdate, countryID);
                setMessage("Director has been added.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }

        if (source==countryComboBox){
            int counter;
            counter = countryComboBox.getSelectedIndex();
            countryID = countries.get(counter).getCountryID();
        }

    }
}
