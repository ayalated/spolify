import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// 底部dock： 主页，搜索，音乐库，创建

public class DockPanel extends JPanel {
    public DockPanel(ActionListener tabSwitchListener) {
        setLayout(new GridLayout(1, 4));

        String[] tabs = {"主页","搜索","音乐库","创建"};
        String[] commands = {"tab1","tab2","tab3","tab4"};

        for (int i = 0; i < tabs.length; i++) {
            JButton btn = new JButton(tabs[i]);
            btn.setActionCommand(commands[i]);
            btn.addActionListener(tabSwitchListener);
            btn.setBorderPainted(false);
            add(btn);
        }
    }

}
