package com.bureau.vault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

/**
 * High-level service for managing the document vault.
 */
public class VaultService {
    private final DocumentDAO dao = new DocumentDAO();
    private final String storageDir = "storage";

    public VaultService() {
        try {
            Files.createDirectories(Paths.get(storageDir));
        } catch (IOException e) {
            System.err.println("[VaultService] Could not create storage directory: " + e.getMessage());
        }
    }

    /**
     * Import a file into the vault
     */
    public void importDocument(File sourceFile, String classification) throws IOException, SQLException {
        String filename = sourceFile.getName();
        Path targetPath = Paths.get(storageDir, filename);

        // Copy file to storage
        Files.copy(sourceFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Save metadata
        Document doc = new Document();
        doc.setFilename(filename);
        doc.setFilePath(targetPath.toString());
        doc.setSize(sourceFile.length());
        doc.setMimeType(Files.probeContentType(sourceFile.toPath()));
        doc.setClassification(classification);

        dao.insert(doc);
    }

    public List<Document> listAllDocuments() throws SQLException {
        return dao.getAll();
    }

    /**
     * Securely purge a document from DB and disk
     */
    public void purgeDocument(Document doc) throws SQLException, IOException {
        // Delete from DB first
        dao.delete(doc.getId());

        // Delete from disk
        Files.deleteIfExists(Paths.get(doc.getFilePath()));
    }
}
