package com.spolify;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CardPanel extends JPanel {
    private final CardLayout cardLayout;
    private final Map<String, JComponent> cardMap = new HashMap<>();

    public CardPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        addTab("tab1", new JLabel("主页内容", SwingConstants.CENTER));
        addTab("tab2", new JLabel("搜索内容", SwingConstants.CENTER));
        addTab("tab3", new PlayListPanel());
       // addTab("tab4", new JLabel("创建内容", SwingConstants.CENTER));
    }

    // 添加卡片
    public void addTab(String key, JComponent comp) {
        add(comp, key);
        cardMap.put(key, comp);
    }
}
