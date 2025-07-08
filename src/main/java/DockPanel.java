import javax.swing.*;
import java.awt.*;

// 底部dock： 主页，搜索，音乐库，创建

public class DockPanel extends JPanel {
    public DockPanel() {
        setLayout(new GridLayout(1, 4));
        JButton homePage = new JButton("主页");
        JButton search = new JButton("搜索");
        JButton musicLib = new JButton("音乐库");
        JButton create = new JButton("创建");

        add(homePage);
        add(search);
        add(musicLib);
        add(create);
    }
}
