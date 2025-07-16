package com.spolify.db;

import java.sql.*;

public class PlaylistDB {
    private static final String DB_URL = "jdbc:sqlite:playlist.db";

    public PlaylistDB() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS playlists (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL UNIQUE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 添加歌单
    public boolean addPlaylist(String name) {
        String sql = "INSERT INTO playlists(name) VALUES(?)";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("添加歌单失败：" + e.getMessage());
            return false;
        }
    }
}
