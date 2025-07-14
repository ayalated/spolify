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
        addTab("tab3", new JLabel("音乐库内容", SwingConstants.CENTER));
        addTab("tab4", new JLabel("创建内容", SwingConstants.CENTER));
    }

    // 添加卡片
    public void addTab(String key, JComponent comp) {
        add(comp, key);
        cardMap.put(key, comp);
    }

    // 切换显示卡片
    public void showTab(String key) {
        cardLayout.show(this, key);
    }

    // 可选：获取指定卡片组件
    public JComponent getTab(String key) {
        return cardMap.get(key);
    }

    // 可选：删除卡片
    public void removeTab(String key) {
        JComponent comp = cardMap.remove(key);
        if (comp != null) remove(comp);
    }
}
