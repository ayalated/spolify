import javax.swing.*;
import java.awt.*;

public class SpolifyPlayer extends JFrame {
    private static final int width = 9*34;
    private static final int height = 16*34;
    private static final String appName = "Spolify";


    public void run() {
        setTitle(appName);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        add(new DockPanel(),BorderLayout.SOUTH);

        setVisible(true);
    }
}
