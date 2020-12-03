package Window;

import Connection.ConnectToSql;
import Window.ElementFrames.Actor.*;
import Window.ElementFrames.Country.*;
import Window.ElementFrames.Director.*;
import Window.ElementFrames.Movie.*;
import Window.ElementFrames.Type.TypeAddFrame;
import Window.ElementFrames.Type.TypeDeleteFrame;
import Window.ElementFrames.Type.TypeSelectFrame;
import Window.ElementFrames.Type.TypeUpdateFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    JPanel statusPanel = new JPanel();

    JLabel statusLabel = new JLabel();

    MenuBar mb = new MenuBar();

    ActorAddFrame actorAddFrame;
    ActorDeleteFrame actorDeleteFrame;
    ActorUpdateFrame actorUpdateFrame;
    ActorSelectFrame actorSelectFrame;

    DirectorAddFrame directorAddFrame;
    DirectorDeleteFrame directorDeleteFrame;
    DirectorSelectFrame directorSelectFrame;
    DirectorUpdateFrame directorUpdateFrame;

    CountryAddFrame countryAddFrame;
    CountryDeleteFrame countryDeleteFrame;
    CountrySelectFrame countrySelectFrame;
    CountryUpdateFrame countryUpdateFrame;

    MovieAddFrame movieAddFrame;
    MovieDeleteFrame movieDeleteFrame;
    MovieUpdateFrame movieUpdateFrame;
    MovieSelectFrame movieSelectFrame;

    TypeAddFrame typeAddFrame;
    TypeDeleteFrame typeDeleteFrame;
    TypeSelectFrame typeSelectFrame;
    TypeUpdateFrame typeUpdateFrame;

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
            directorAddFrame = new DirectorAddFrame();
            statusLabel.setText(directorAddFrame.getMessage());
        }
        if(source==mb.menus[0].getItem(2)){
            movieAddFrame = new MovieAddFrame();
            statusLabel.setText(movieAddFrame.getMessage());
        }
        if(source==mb.menus[0].getItem(3)){
            countryAddFrame = new CountryAddFrame();
            statusLabel.setText(countryAddFrame.getMessage());
        }
        if(source==mb.menus[0].getItem(4)){
            typeAddFrame = new TypeAddFrame();
            statusLabel.setText(typeAddFrame.getMessage());
        }
        if(source==mb.menus[0].getItem(5)){

        }

        //delete
        if(source==mb.menus[1].getItem(0)){
            actorDeleteFrame = new ActorDeleteFrame();
            statusLabel.setText(actorDeleteFrame.getMessage());
        }
        if(source==mb.menus[1].getItem(1)){
            directorDeleteFrame = new DirectorDeleteFrame();
            statusLabel.setText(directorDeleteFrame.getMessage());
        }
        if(source==mb.menus[1].getItem(2)){
            movieDeleteFrame = new MovieDeleteFrame();
            statusLabel.setText(movieDeleteFrame.getMessage());
        }
        if(source==mb.menus[1].getItem(3)){
            countryDeleteFrame = new CountryDeleteFrame();
            statusLabel.setText(countryDeleteFrame.getMessage());
        }
        if(source==mb.menus[1].getItem(4)){
            typeDeleteFrame = new TypeDeleteFrame();
            statusLabel.setText(typeDeleteFrame.getMessage());
        }
        if(source==mb.menus[1].getItem(5)){

        }

        //select
        if(source==mb.menus[2].getItem(0)){
            actorSelectFrame = new ActorSelectFrame();
            statusLabel.setText(actorSelectFrame.getMessage());
        }
        if(source==mb.menus[2].getItem(1)){
            directorSelectFrame = new DirectorSelectFrame();
            statusLabel.setText(directorSelectFrame.getMessage());
        }
        if(source==mb.menus[2].getItem(2)){
            movieSelectFrame = new MovieSelectFrame();
            statusLabel.setText(movieSelectFrame.getMessage());
        }
        if(source==mb.menus[2].getItem(3)){
            countrySelectFrame = new CountrySelectFrame();
            statusLabel.setText(countrySelectFrame.getMessage());
        }
        if(source==mb.menus[2].getItem(4)){
            typeSelectFrame = new TypeSelectFrame();
            statusLabel.setText(typeSelectFrame.getMessage());
        }
        if(source==mb.menus[2].getItem(5)){

        }

        //update
        if(source==mb.menus[3].getItem(0)){
            actorUpdateFrame = new ActorUpdateFrame();
            statusLabel.setText(actorUpdateFrame.getMessage());
        }
        if(source==mb.menus[3].getItem(1)){
            directorUpdateFrame = new DirectorUpdateFrame();
            statusLabel.setText(directorUpdateFrame.getMessage());
        }
        if(source==mb.menus[3].getItem(2)){
            movieUpdateFrame = new MovieUpdateFrame();
            statusLabel.setText(movieUpdateFrame.getMessage());
        }
        if(source==mb.menus[3].getItem(3)){
            countryUpdateFrame = new CountryUpdateFrame();
            statusLabel.setText(countryUpdateFrame.getMessage());
        }
        if(source==mb.menus[3].getItem(4)){
            typeUpdateFrame = new TypeUpdateFrame();
            statusLabel.setText(typeUpdateFrame.getMessage());
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
