package com.spolify.ui;

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
        DockPanel dock = new DockPanel(e -> switchTab(e.getActionCommand()),()->{
            String name = JOptionPane.showInputDialog(this, "请输入新歌单名称：", "创建歌单", JOptionPane.PLAIN_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                // 调用 PlaylistPanel 或 JTree 相关方法新增歌单节点
//                playlistPanel.addPlaylist(name);
            }
        });
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
