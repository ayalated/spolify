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
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(p);
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


        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem playItem = new JMenuItem("播放");
        JMenuItem renameItem = new JMenuItem("重命名");
        JMenuItem deleteItem = new JMenuItem("删除");

        popupMenu.add(playItem);
        popupMenu.add(renameItem);
        popupMenu.add(deleteItem);

        // 响应歌单节点点击
        playlistTree.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    TreePath path = playlistTree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        playlistTree.setSelectionPath(path);
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        Object userObject = node.getUserObject();

                        if (node.isLeaf()) {
                            popupMenu.show(playlistTree, e.getX(), e.getY());

                            String playlistName = node.getUserObject().toString();

                            if (userObject instanceof Playlist p) {
                                String code = p.getCode();
                                String name = p.getName();
                                playItem.addActionListener(ev -> {
                                    JOptionPane.showMessageDialog(PlaylistPanel.this, "播放歌单：" + name);
                                });

                                if (!"已点赞的歌曲".equals(name)) {
                                    renameItem.addActionListener(ev -> {
                                        String newName = JOptionPane.showInputDialog("重命名为：", name);
                                        if (newName != null && !newName.trim().isEmpty()) {
                                            node.setUserObject(newName);
                                            ((DefaultTreeModel) playlistTree.getModel()).nodeChanged(node);

                                            PlaylistDB db = new PlaylistDB();
                                            db.renamePlaylist(code, newName);
                                        }
                                    });

                                    deleteItem.addActionListener(ev -> {
                                        int result = JOptionPane.showConfirmDialog(PlaylistPanel.this, "确定要删除" + name + "吗？", "确认", JOptionPane.YES_NO_OPTION);
                                        if (result == JOptionPane.YES_OPTION) {
                                            PlaylistDB db = new PlaylistDB();
                                            db.deletePlaylist(code);
                                            ((DefaultMutableTreeNode) playlistTree.getModel().getRoot()).remove(node);
                                            ((DefaultTreeModel) playlistTree.getModel()).reload();
                                        }
                                    });
                                }
                            }
                        }
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
