import javax.swing.*;

public class HelloSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello Swing");
        frame.setSize(300, 200);

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);


        JLabel label = new JLabel("Hello Java GUI!", SwingConstants.CENTER);
        JButton btn = new JButton("click me");
        btn.setSize(20,20);

        frame.add(label);
        frame.add(btn);


        frame.setVisible(true);
    }
}
