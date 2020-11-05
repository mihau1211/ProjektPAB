package Window;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyFrame extends JFrame{
    public MyFrame() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        MenuBar mb = new MenuBar();

        setJMenuBar(mb.mb);
        setLayout(null);
        setVisible(true);
    }
}
