package com.bureau.vault;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Document operations.
 */
public class DocumentDAO {

    public void insert(Document doc) throws SQLException {
        String sql = "INSERT INTO documents(filename, file_path, size, mime_type, classification) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doc.getFilename());
            pstmt.setString(2, doc.getFilePath());
            pstmt.setLong(3, doc.getSize());
            pstmt.setString(4, doc.getMimeType());
            pstmt.setString(5, doc.getClassification());
            pstmt.executeUpdate();
        }
    }

    public List<Document> getAll() throws SQLException {
        List<Document> docs = new ArrayList<>();
        String sql = "SELECT * FROM documents ORDER BY created_at DESC";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Document doc = new Document();
                doc.setId(rs.getInt("id"));
                doc.setFilename(rs.getString("filename"));
                doc.setFilePath(rs.getString("file_path"));
                doc.setSize(rs.getLong("size"));
                doc.setMimeType(rs.getString("mime_type"));
                doc.setClassification(rs.getString("classification"));
                // Note: Simplified date handling for demonstration
                docs.add(doc);
            }
        }
        return docs;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM documents WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
