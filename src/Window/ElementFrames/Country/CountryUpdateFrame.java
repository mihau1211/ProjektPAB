package Window.ElementFrames.Country;

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

public class CountryUpdateFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Country country = new Country();

    ArrayList<Country> countries = new ArrayList<Country>();

    JTextField nameField = new JTextField();

    JButton okButton1 = new JButton("OK");
    JButton okButton2 = new JButton("OK");

    JLabel title = new JLabel("To update country type data in box below.");
    JLabel nameLabel = new JLabel("Name:");

    JScrollPane pane;

    JTable table;

    String message;
    String[] columnNames = {"ID","Name"};
    long countryID;

    String name;

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

    public void countrySetDataFrame(){
        setLayout(null);
        setModal(true);
        setTitle("Update Country");
        connectToSql.startConnection();

        nameField.setBounds(150, 50, 300, 30);

        okButton2.setBounds(230, 250, 120, 40);
        okButton2.addActionListener(this);

        title.setBounds(50,20,400,30);
        nameLabel.setBounds(50,50,70,30);

        add(nameField);

        add(okButton2);

        add(title);
        add(nameLabel);

        setSize(500,350);
        setVisible(true);
    }

    public CountryUpdateFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Update Country");
        connectToSql.startConnection();

        okButton1.setBounds(180, 360, 120, 40);
        okButton1.addActionListener(this);

        countries = country.getCountries(connectToSql.conn);
        Object[][] rows = new Object[countries.size()][2];
        for (int i=0; i<countries.size(); i++){
            for (int j=0; j<2; j++){
                if (j==0)rows[i][j] = countries.get(i).getCountryID();
                if (j==1)rows[i][j] = countries.get(i).getName();
            }
        }

        table = new JTable(rows, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() > -1) {
                    countryID= Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
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

        if (source == okButton1) {
            countrySetDataFrame();
            pane.setVisible(false);
//            dispose();
        }

        if (source==okButton2){
            try {
                country.updateCountry(connectToSql.conn, name, countryID);
                setMessage("Country has been updated.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }
    }
}
