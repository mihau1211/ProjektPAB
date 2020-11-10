package Window;

import javax.swing.*;
import java.util.ArrayList;

public class MenuBar {
    String[] tables = {"ACTOR", "DIRECTOR", "MOVIE", "COUNTRY", "TYPE", "AHM"};
    String[] items = {"ADD","DELETE","SELECT","UPDATE"};
    JMenuBar mb = new JMenuBar();

    MenuBar() {
        JMenu menus[] = new JMenu[tables.length];

        for (int i = 0; i < tables.length; i++) {
            menus[i] = new JMenu(tables[i]);
            for (int j = 0; j < items.length; j++) {
                menus[i].add(new JMenuItem(items[j]));
            }
            mb.add(menus[i]);
        }
    }
}
