package com.spolify.ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CardPanel extends JPanel {
    private final CardLayout cardLayout;
    private final Map<String, JComponent> cardMap = new HashMap<>();
    private final PlaylistPanel playlistPanel = new PlaylistPanel();

    public CardPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        addTab("tab1", new JLabel("主页内容", SwingConstants.CENTER));
        addTab("tab2", new JLabel("搜索内容", SwingConstants.CENTER));
        addTab("tab3", playlistPanel);
       // addTab("tab4", new JLabel("创建内容", SwingConstants.CENTER));
    }

    // 添加卡片
    public void addTab(String key, JComponent comp) {
        add(comp, key);
        cardMap.put(key, comp);
    }

    public PlaylistPanel getPlaylistPanel() {
        return (PlaylistPanel) cardMap.get("tab3");
    }

}
