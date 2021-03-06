package Window;

import DBElements.Actor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Connection.*;

public class MenuBar{
    String[] tables = {"Actor...", "Director...", "Movie...", "Country...", "Type...", "AHM..."};
    String[] items = {"Add","Delete","Select","Update"};
    JMenuBar mb = new JMenuBar();
    JMenu menus[] = new JMenu[items.length];

    MenuBar() {

        //dodanie obiektow menu w aplikacji
        for (int i = 0; i < items.length; i++) {
            menus[i] = new JMenu(items[i]);
            for (int j = 0; j < tables.length; j++) {
                if ((i!=3 || j!=5)&&(i!=1 || j!= 5)) {
                    menus[i].add(new JMenuItem(tables[j]));
                }
            }
            mb.add(menus[i]);
        }

    }
}
