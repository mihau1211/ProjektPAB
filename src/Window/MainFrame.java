package Window;

import Connection.ConnectToSql;
import DBElements.*;
import Window.ElementFrames.ActorAddFrame;
import Window.ElementFrames.ActorDeleteFrame;
import Window.ElementFrames.ActorSelectFrame;
import Window.ElementFrames.ActorUpdateFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    JPanel statusPanel = new JPanel();

    JLabel statusLabel = new JLabel();

    MenuBar mb = new MenuBar();

    ActorAddFrame actorAddFrame;
    ActorDeleteFrame actorDeleteFrame;
    ActorUpdateFrame actorUpdateFrame;
    ActorSelectFrame actorSelectFrame;

    ConnectToSql connectToSql = new ConnectToSql();
    public MainFrame() {
        super("Baza filmowa");
        setLayout(new BorderLayout());
        connectToSql.startConnection();

        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);

        //dodanie listenerow
        for (int i=0; i<mb.menus.length; i++){
            mb.menus[i].addActionListener(this);
            for (int j=0; j<mb.tables.length; j++){
                if (i!=3 || j!=5) {
                    mb.menus[i].getItem(j).addActionListener(this);
                }
            }
        }

        setJMenuBar(mb.mb);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

////warunki dzialania listenerow

        //insert
        if(source==mb.menus[0].getItem(0)){
            actorAddFrame = new ActorAddFrame();
            statusLabel.setText(actorAddFrame.getMessage());
        }
        if(source==mb.menus[0].getItem(1)){

        }
        if(source==mb.menus[0].getItem(2)){

        }
        if(source==mb.menus[0].getItem(3)){

        }

        if(source==mb.menus[0].getItem(4)){

        }
        if(source==mb.menus[0].getItem(5)){

        }

        //delete
        if(source==mb.menus[1].getItem(0)){
            actorDeleteFrame = new ActorDeleteFrame();
            statusLabel.setText(actorDeleteFrame.getMessage());
        }
        if(source==mb.menus[1].getItem(1)){

        }
        if(source==mb.menus[1].getItem(2)){

        }
        if(source==mb.menus[1].getItem(3)){

        }
        if(source==mb.menus[1].getItem(4)){

        }
        if(source==mb.menus[1].getItem(5)){

        }

        //select
        if(source==mb.menus[2].getItem(0)){
            actorSelectFrame = new ActorSelectFrame();
            statusLabel.setText(actorSelectFrame.getMessage());
        }
        if(source==mb.menus[2].getItem(1)){

        }
        if(source==mb.menus[2].getItem(2)){

        }
        if(source==mb.menus[2].getItem(3)){

        }
        if(source==mb.menus[2].getItem(4)){

        }
        if(source==mb.menus[2].getItem(5)){

        }

        //update
        if(source==mb.menus[3].getItem(0)){
            actorUpdateFrame = new ActorUpdateFrame();
            statusLabel.setText(actorUpdateFrame.getMessage());
        }
        if(source==mb.menus[3].getItem(1)){

        }
        if(source==mb.menus[3].getItem(2)){

        }
        if(source==mb.menus[3].getItem(3)){

        }
        if(source==mb.menus[3].getItem(4)){

        }










        //test-dla kazdego obiektu menu konsola wyswietla jego pozycje w tablicy
//        for (int i=0; i<mb.menus.length; i++){
//            for (int j=0; j<4; j++){
//                if(source==mb.menus[i].getItem(j)){
//                    System.out.println("I:  "+i+"\nJ:  "+j);
//                }
//            }
//        }
    }
}
