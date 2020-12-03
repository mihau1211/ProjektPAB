package Window.ElementFrames.Type;

import Connection.ConnectToSql;
import DBElements.Country;
import DBElements.Type;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TypeSelectFrame extends JDialog implements ActionListener {
    ConnectToSql connectToSql = new ConnectToSql();
    DBElements.Type type = new DBElements.Type();

    ArrayList<DBElements.Type> types;

    JButton okButton = new JButton("OK");

    JTable table;

    String message;
    String[] columnNames = {"ID","Name"};

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public TypeSelectFrame() {
        setLayout(null);
        setModal(true);
        setTitle("Select Types");
        connectToSql.startConnection();

        okButton.setBounds(180, 360, 120, 40);
        okButton.addActionListener(this);

        add(okButton);

        types = type.getTypes(connectToSql.conn);
        Object[][] rows = new Object[types.size()][2];
        for (int i=0; i<types.size(); i++){
            for (int j=0; j<2; j++){
                if (j==0)rows[i][j] = types.get(i).getTypeID();
                if (j==1)rows[i][j] = types.get(i).getName();
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
            setMessage("Types has been selected.");
            dispose();
        }
    }
}
