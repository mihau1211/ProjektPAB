package Window.ElementFrames.Movie;

import Connection.ConnectToSql;
import DBElements.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieUpdateFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Movie movie = new Movie();
    Country country = new Country();
    Director director = new Director();
    DBElements.Type type = new DBElements.Type();

    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<Country> countries = new ArrayList<Country>();
    ArrayList<DBElements.Type> types = new ArrayList<DBElements.Type>();
    ArrayList<Director> directors = new ArrayList<Director>();

    JComboBox countryComboBox;
    JComboBox typeComboBox;
    JComboBox directorComboBox;

    JTextField nameField = new JTextField();
    JTextField yearField = new JTextField();
    JTextField ratingField = new JTextField();

    JButton okButton1 = new JButton("OK");
    JButton okButton2 = new JButton("OK");

    JScrollPane pane;

    JLabel title = new JLabel("To update movie type data in boxes below.");
    JLabel nameLabel = new JLabel("Name:");
    JLabel yearLabel = new JLabel("Year:");
    JLabel ratingLabel = new JLabel("Rating:");
    JLabel countryLabel = new JLabel("Country:");
    JLabel directorLabel = new JLabel("Director:");
    JLabel typeLabel = new JLabel("Type:");

    String comboCountryNames[];
    String comboTypeNames[];
    String comboDirectorNames[];
    String name;
    String year;
    int rating;
    long typeID = 1;
    long directorID;
    long countryID = 1;

    JTable table;

    String message;
    String[] columnNames = {"ID","Name","Year","Rating","Director","Type","Country"};
    long id;

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String getName() {
        return nameField.getText();
    }

    public String getYear() {
        return yearField.getText();
    }

    public String getRating() {
        return ratingField.getText();
    }

    public String getMessage(){
        return message;
    }

    public void movieSetDataFrame(){
        setLayout(null);
        setModal(true);
        setTitle("Update Movie");
        connectToSql.startConnection();


        try {
            countries = country.getCountries(connectToSql.conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboCountryNames = new String[countries.size()];
        for (int i=0; i<countries.size(); i++){
            comboCountryNames[i] = countries.get(i).getName();
        }

        try {
            directors = director.getDirectors(connectToSql.conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboDirectorNames = new String[directors.size()];
        for (int i=0; i<directors.size(); i++){
            comboDirectorNames[i] = directors.get(i).getName();
        }
        directorID = directors.get(0).getDirectorID();

        try {
            types = type.getTypes(connectToSql.conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboTypeNames = new String[types.size()];
        for (int i=0; i<types.size(); i++){
            comboTypeNames[i] = types.get(i).getName();
        }

        countryComboBox = new JComboBox(comboCountryNames);
        countryComboBox.setBounds(150, 200, 300, 30);
        countryComboBox.addActionListener(this);

        directorComboBox = new JComboBox(comboDirectorNames);
        directorComboBox.setBounds(150, 250, 300, 30);
        directorComboBox.addActionListener(this);

        typeComboBox = new JComboBox(comboTypeNames);
        typeComboBox.setBounds(150, 300, 300, 30);
        typeComboBox.addActionListener(this);

        nameField.setBounds(150, 50, 300, 30);
        yearField.setBounds(150, 100, 300, 30);
        ratingField.setBounds(150, 150, 300, 30);

        okButton2.setBounds(230, 350, 120, 40);
        okButton2.addActionListener(this);

        title.setBounds(50,20,400,30);
        nameLabel.setBounds(50,50,70,30);
        yearLabel.setBounds(50,100,70,30);
        ratingLabel.setBounds(50,150,70,30);
        countryLabel.setBounds(50,200,70,30);
        directorLabel.setBounds(50,250,70,30);
        typeLabel.setBounds(50,300,70,30);

        add(nameField);
        add(yearField);
        add(ratingField);

        add(countryComboBox);
        add(directorComboBox);
        add(typeComboBox);

        add(okButton2);

        add(title);
        add(nameLabel);
        add(yearLabel);
        add(ratingLabel);
        add(countryLabel);
        add(directorLabel);
        add(typeLabel);

        setSize(500,450);
        setVisible(true);
    }

    public MovieUpdateFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Update Movie");
        connectToSql.startConnection();

        okButton1.setBounds(180, 360, 120, 40);
        okButton1.addActionListener(this);

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
        year = getYear();
//        rating = Integer.parseInt(getRating());

        if (source == okButton1) {
            movieSetDataFrame();
            pane.setVisible(false);
            remove(okButton1);
        }

        if (source==okButton2){
            try {
                rating = Integer.parseInt(getRating());
                movie.updateMovie(connectToSql.conn, name, year, rating, countryID, typeID, directorID, id);
                setMessage("Movie has been updated.");
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

        if (source==directorComboBox){
            int counter;
            counter = directorComboBox.getSelectedIndex();
            directorID = directors.get(counter).getDirectorID();
        }

        if (source==typeComboBox){
            int counter;
            counter = typeComboBox.getSelectedIndex();
            typeID = types.get(counter).getTypeID();
        }

    }
}
