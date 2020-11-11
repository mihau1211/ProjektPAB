package Window;

import Connection.ConnectToSql;
import DBElements.Actor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {
    MenuBar mb = new MenuBar();
    Actor actor = new Actor();
    ConnectToSql connectToSql = new ConnectToSql();
    public MyFrame() {
        super("Baza filmowa");
        connectToSql.startConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);

        //dodanie listenerow
        for (int i=0; i<mb.menus.length; i++){
            for (int j=0; j<mb.items.length; j++){
                mb.menus[i].getItem(j).addActionListener(this);
            }
        }




        setJMenuBar(mb.mb);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

////warunki dzialania listenerow
//        if(source==mb.menus[0].getItem(0)){
//            try {
//                actor.selectActor(connectToSql.conn);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

        //test-dla kazdego obiektu menu konsola wyswietla jego pozycje w tablicy
        for (int i=0; i<6; i++){
            for (int j=0; j<4; j++){
                if(source==mb.menus[i].getItem(j)){
                    System.out.println("I:  "+i+"\nJ:  "+j);
                }
            }
        }
    }
}
