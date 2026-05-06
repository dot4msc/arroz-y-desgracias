package com.bureau.vault;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles SQLite database connection and initialization.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:vault.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS documents (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "filename TEXT NOT NULL," +
                     "file_path TEXT NOT NULL," +
                     "size INTEGER," +
                     "mime_type TEXT," +
                     "classification TEXT DEFAULT 'RESTRICTED'," +
                     "created_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
                     ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("[Database] SQLite initialized and table verified.");
        } catch (SQLException e) {
            System.err.println("[Database] Initialization failed: " + e.getMessage());
        }
    }
}
