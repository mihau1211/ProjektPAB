package Window;

import DBElements.Actor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Connection.*;

public class MenuBar{
    String[] tables = {"ACTOR", "DIRECTOR", "MOVIE", "COUNTRY", "TYPE", "AHM"};
    String[] items = {"ADD","DELETE","SELECT","UPDATE"};
    JMenuBar mb = new JMenuBar();
    JMenu menus[] = new JMenu[tables.length];

    MenuBar() {

        //dodanie obiektow menu w aplikacji
        for (int i = 0; i < tables.length; i++) {
            menus[i] = new JMenu(tables[i]);
            for (int j = 0; j < items.length; j++) {
                menus[i].add(new JMenuItem(items[j]));
            }
            mb.add(menus[i]);
        }

    }
}
