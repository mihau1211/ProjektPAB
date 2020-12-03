package Window.ElementFrames.Director;

import Connection.ConnectToSql;
import DBElements.Actor;
import DBElements.Country;
import DBElements.Director;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class DirectorUpdateFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Director director = new Director();
    Country country = new Country();

    ArrayList<Director> directors;
    ArrayList<Country> countries = new ArrayList<Country>();

    JComboBox countryComboBox;

    JTextField nameField = new JTextField();
    JTextField surnameField = new JTextField();
    JTextField birthdateField = new JTextField();

    JButton okButton1 = new JButton("OK");
    JButton okButton2 = new JButton("OK");

    JLabel title = new JLabel("To update director type data in boxes below.");
    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel birthdateLabel = new JLabel("Birthdate:");
    JLabel countryLabel = new JLabel("Country:");

    JScrollPane pane;

    JTable table;

    String message;
    String[] columnNames = {"ID","Name","Surname","Birthdate","Country"};
    long directorID;

    String comboCountryNames[];
    String name;
    String surname;
    String birthdate;
    long countryID = 1;

    DirectorAddFrame directorAddFrame;

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

    public void directorSetDataFrame(){
        setLayout(null);
        setModal(true);
        setTitle("Update Director");
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

        okButton2.setBounds(230, 250, 120, 40);
        okButton2.addActionListener(this);

        title.setBounds(50,20,400,30);
        nameLabel.setBounds(50,50,70,30);
        surnameLabel.setBounds(50,100,70,30);
        birthdateLabel.setBounds(50,150,70,30);
        countryLabel.setBounds(50,200,70,30);

        add(nameField);
        add(surnameField);
        add(birthdateField);

        add(countryComboBox);

        add(okButton2);

        add(title);
        add(nameLabel);
        add(surnameLabel);
        add(birthdateLabel);
        add(countryLabel);

        setSize(500,350);
        setVisible(true);
    }

    public DirectorUpdateFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Update Director");
        connectToSql.startConnection();

        okButton1.setBounds(180, 360, 120, 40);
        okButton1.addActionListener(this);

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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() > -1) {
                    directorID= Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
                }
            }
        });

        pane = new JScrollPane(table);
        pane.setBounds(40,50,400, 300);
        pane.setVisible(true);
        add(pane);

        add(okButton1);

        setSize(500,500);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        name = getName();
        surname = getSurname();
        birthdate = getBirthdate();

        if (source == okButton1) {
            directorSetDataFrame();
            pane.setVisible(false);
//            dispose();
        }

        if (source==okButton2){
            try {
                director.updateDirector(connectToSql.conn, name, surname, birthdate, countryID, directorID);
                setMessage("Director has been updated.");
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
