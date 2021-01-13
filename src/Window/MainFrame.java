package Window;

import Connection.ConnectToSql;
import DBElements.ActorHasMovie;
import DBElements.Movie;
import Window.ElementFrames.AHM.AHMAddFrame;
import Window.ElementFrames.AHM.AHMSelectFrame;
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
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    Object[][] rows;

    JPanel statusPanel = new JPanel();

    JLabel statusLabel = new JLabel();

    Movie movie = new Movie();
    ArrayList<Movie> movies;

    JTable table;

    MenuBar mb = new MenuBar();

    JButton printButton = new JButton("Print movies list");

    CreatePDF createPDF = new CreatePDF();
    PrintPDF printPDF = new PrintPDF();

    ActorAddFrame actorAddFrame;
    ActorDeleteFrame actorDeleteFrame;
    ActorUpdateFrame actorUpdateFrame;
    ActorSelectFrame actorSelectFrame;

    AHMAddFrame ahmAddFrame;
    AHMSelectFrame ahmSelectFrame;

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

    public void getMoviesToTable(){
        movies = movie.getMovies(connectToSql.conn);
        rows = new Object[movies.size()][7];
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
    }

    public MainFrame() {
        super("Baza filmowa");
        setLayout(new BorderLayout());
        connectToSql.startConnection();

        //// Domyślnie wyświetl filmy
        getMoviesToTable();

        String[] columnNames = {"ID","Name","Year","Rating","Director","Type","Country"};
        table = new JTable(rows, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        printButton.addActionListener(this);
        printButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(printButton, BorderLayout.EAST);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40,50,400, 300);
        pane.setVisible(true);
        add(pane);
        //////

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
                if ((i!=3 || j!=5) && (i!=1 || j!=5)) {
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
            getMoviesToTable();
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
            ahmAddFrame = new AHMAddFrame();
            statusLabel.setText(ahmAddFrame.getMessage());
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
            getMoviesToTable();
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
            ahmSelectFrame = new AHMSelectFrame();
            statusLabel.setText(ahmSelectFrame.getMessage());
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
            getMoviesToTable();
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

        if (source==printButton){
            try {
                createPDF.PDFcreate(movies);
                printPDF.printPDF(createPDF.getFileName());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
