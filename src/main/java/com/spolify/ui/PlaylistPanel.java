package com.spolify.ui;

import com.spolify.db.PlaylistDB;
import com.spolify.model.Playlist;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlaylistPanel extends JPanel {
    private DefaultMutableTreeNode root;
    private JTree playlistTree;
    private DefaultTreeModel treeModel;

    public PlaylistPanel() {
        setLayout(new BorderLayout());

        root = new DefaultMutableTreeNode("我的音乐");

        PlaylistDB db = new PlaylistDB();
        List<Playlist> lists = db.getAllPlaylists();
        for (Playlist p : lists) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(p.getName());
            root.add(group);
        }


        // 创建JTree
        treeModel = new DefaultTreeModel(root);
        playlistTree = new JTree(treeModel);

        // 设置只展开到一级
        playlistTree.expandRow(0);

        playlistTree.setRootVisible(false);

        playlistTree.setRowHeight(38);
        playlistTree.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) playlistTree.getCellRenderer();
        renderer.setBackgroundNonSelectionColor(new Color(250, 250, 255));
        renderer.setBackgroundSelectionColor(new Color(30, 144, 255, 64));
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


    public void addPlaylistToTree(Playlist p) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(p.getName());
        root.add(newNode);
        treeModel.reload(root);
        playlistTree.expandRow(0);
    }

    public void refreshTree() {
        root.removeAllChildren();
        PlaylistDB db = new PlaylistDB();
        List<Playlist> lists = db.getAllPlaylists();

        for (Playlist p : lists) {
            root.add(new DefaultMutableTreeNode(p.getName()));
        }

        treeModel.reload();
        playlistTree.expandRow(0);
    }
}
