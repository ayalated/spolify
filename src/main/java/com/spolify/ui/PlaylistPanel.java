package com.spolify.ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaylistPanel extends JPanel {
    public PlaylistPanel() {
        setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的音乐");

        // 歌单分组1
        DefaultMutableTreeNode favGroup = new DefaultMutableTreeNode("收藏歌单");
        favGroup.add(new DefaultMutableTreeNode("日语精选"));
        favGroup.add(new DefaultMutableTreeNode("安静午后"));

        // 歌单分组2
        DefaultMutableTreeNode hisGroup = new DefaultMutableTreeNode("历史播放");
        hisGroup.add(new DefaultMutableTreeNode("2024-经典老歌"));
        hisGroup.add(new DefaultMutableTreeNode("2025-新碟上架"));

        // 歌单分组3
        DefaultMutableTreeNode selfGroup = new DefaultMutableTreeNode("自建歌单");
        selfGroup.add(new DefaultMutableTreeNode("电子合成器"));
        selfGroup.add(new DefaultMutableTreeNode("流行快节奏"));

        root.add(favGroup);
        root.add(hisGroup);
        root.add(selfGroup);

        // 创建JTree
        JTree playlistTree = new JTree(root);

        // 设置只展开到一级
        for (int i = 0; i < playlistTree.getRowCount(); i++) {
            playlistTree.expandRow(1);
        }

        playlistTree.setRootVisible(false);

        playlistTree.setRowHeight(38);
        playlistTree.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) playlistTree.getCellRenderer();
        renderer.setBackgroundNonSelectionColor(new Color(250,250,255));
        renderer.setBackgroundSelectionColor(new Color(30,144,255,64));
        renderer.setTextSelectionColor(Color.BLACK);

        // 响应歌单节点点击（例：打印被选中的歌单名）
        playlistTree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selRow = playlistTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = playlistTree.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1 && e.getClickCount() == 2) { // 双击
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    if (node.isLeaf()) {
                        JOptionPane.showMessageDialog(PlaylistPanel.this,
                                "你点击了歌单：" + node.getUserObject().toString(),
                                "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(playlistTree);

        scrollPane.setBorder(BorderFactory.createBevelBorder(15));

        add(scrollPane, BorderLayout.CENTER);
    }
}
