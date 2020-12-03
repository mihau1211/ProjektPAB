package Window.ElementFrames.Country;

import Connection.ConnectToSql;
import DBElements.Actor;
import DBElements.Country;
import Window.MainFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDeleteFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    Country country = new Country();

    ArrayList<Country> countries;

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"ID","Name"};
    long id;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public CountryDeleteFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Delete Country");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        add(okButton);
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
                    id= Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
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
                country.deleteCountryByID(connectToSql.conn, id);
                setMessage("Country has been removed.");
                dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                setMessage("ERROR: You typed wrong data.");
                dispose();
            }
        }
    }
}
