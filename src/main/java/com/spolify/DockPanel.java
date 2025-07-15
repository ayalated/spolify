package com.spolify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

// 底部dock： 主页，搜索，音乐库，创建

public class DockPanel extends JPanel {
    public DockPanel(ActionListener tabSwitchListener) {
        setLayout(new GridLayout(1, 4));

        String[] tabs = {"主页","搜索","音乐库","创建"};
        String[] commands = {"tab1","tab2","tab3","tab4"};
        String[] icons = {"/icons8-home-96.png", "/icons8-search-48.png", "/icons8-library-48.png", "/icons8-plus-48.png"};

        for (int i = 0; i < tabs.length; i++) {
            JButton btn = new JButton(tabs[i]);
            btn.setIcon(loadIcon(icons[i],24,24));
            btn.setActionCommand(commands[i]);
            btn.addActionListener(tabSwitchListener);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false); // 只要图标和文字
            btn.setOpaque(true);
            btn.setBackground(new Color(250, 250, 250)); // 浅色背景
            btn.setFont(new Font("微软雅黑", Font.BOLD,12));
            btn.putClientProperty("JButton.buttonType", "toolBarButton"); // 扁平化
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            add(btn);
        }
    }


    public ImageIcon loadIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

}
