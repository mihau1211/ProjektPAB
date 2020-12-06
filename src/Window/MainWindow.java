package Window;

import java.awt.*;
import java.io.IOException;

public class MainWindow {
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
