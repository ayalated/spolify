package com.spolify.db;

import com.spolify.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class PlaylistDB {
    private static final String DB_URL = "jdbc:sqlite:playlist.db";

    public PlaylistDB() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS playlists (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name TEXT NOT NULL UNIQUE, " +
                            "code TEXT NOT NULL UNIQUE)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加歌单
    public boolean addPlaylist(String name) {
        String sql = "INSERT INTO playlists(code,name) VALUES(?,?)";
        String code = randomUUID().toString();
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.setString(2, name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("添加歌单失败：" + e.getMessage());
            return false;
        }
    }

    // 删除歌单
    public boolean deletePlaylist(String code) {
        String sql = "DELETE FROM playlists WHERE code=?";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("删除歌单失败：" + e.getMessage());
            return false;
        }
    }

    // 重命名歌单
    public boolean renamePlaylist(String code, String newName) {
        String sql = "UPDATE playlists SET name=? WHERE code =?";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, code);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("重命名歌单失败：" + e.getMessage());
            return false;
        }
    }

    // 获取所有歌单
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        Playlist pinned = null;
        String sql = "SELECT code,name FROM playlists ORDER by id DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                Playlist p = new Playlist(code,name);

                if ("已点赞的歌曲".equals(name)){
                    pinned = p;
                }else {
                    playlists.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (pinned != null){
            playlists.addFirst(pinned);
        }

        return playlists;
    }

    public Playlist getByName(String name) {
        String sql = "SELECT code,name FROM playlists WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String code = rs.getString("code");
                return new Playlist(code, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 没找到
    }

}
