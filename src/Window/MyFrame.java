package Window;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame{
    public MyFrame() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);

        JMenu menuActor, menuDirector;
        JMenuItem itemAdd, itemDelete, itemSelect, itemUpdate;

        JMenuBar mbActor=new JMenuBar();
        menuActor=new JMenu("ACTOR");
        itemAdd=new JMenuItem("ADD");
        itemDelete=new JMenuItem("DELETE");
        itemSelect=new JMenuItem("SELECT");
        itemUpdate=new JMenuItem("UPDATE");
        menuActor.add(itemAdd); menuActor.add(itemDelete); menuActor.add(itemSelect); menuActor.add(itemUpdate);
        mbActor.add(menuActor);
        setJMenuBar(mbActor);

        JMenuBar mbDirector=new JMenuBar();
        menuActor=new JMenu("DIRECTOR");
        itemAdd=new JMenuItem("ADD");
        itemDelete=new JMenuItem("DELETE");
        itemSelect=new JMenuItem("SELECT");
        itemUpdate=new JMenuItem("UPDATE");
        menuActor.add(itemAdd); menuActor.add(itemDelete); menuActor.add(itemSelect); menuActor.add(itemUpdate);
        mbDirector.add(menuActor);
        setJMenuBar(mbDirector);
        mbActor.setBounds(0,0,20,10);

        setLayout(null);
        setVisible(true);
    }
}
