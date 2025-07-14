import javax.swing.*;
import java.awt.*;

public class SpolifyPlayer extends JFrame {
    private static final int width = 9*34;
    private static final int height = 16*34;
    private static final String appName = "Spolify";
    private JPanel cardPanel;

    public void run() {
        setTitle(appName);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // 1. 内容区（CardLayout）
        cardPanel = new CardPanel();

        add(cardPanel, BorderLayout.CENTER);

        // 2. 底部 dock 栏
        DockPanel dock = new DockPanel(e -> switchTab(e.getActionCommand()));
        add(dock, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
    }

    // 切换内容卡片
    private void switchTab(String tabKey) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, tabKey);
    }

}
