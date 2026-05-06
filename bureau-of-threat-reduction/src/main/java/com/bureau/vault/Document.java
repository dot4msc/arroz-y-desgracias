package com.bureau.vault;

import java.time.LocalDateTime;

/**
 * Model class representing a document in the vault.
 */
public class Document {
    private int id;
    private String filename;
    private String filePath;
    private long size;
    private String mimeType;
    private String classification;
    private LocalDateTime createdAt;

    public Document() {}

    public Document(int id, String filename, String filePath, long size, String mimeType, String classification, LocalDateTime createdAt) {
        this.id = id;
        this.filename = filename;
        this.filePath = filePath;
        this.size = size;
        this.mimeType = mimeType;
        this.classification = classification;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }

    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }

    public String getClassification() { return classification; }
    public void setClassification(String classification) { this.classification = classification; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
