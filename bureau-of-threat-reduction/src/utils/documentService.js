/**
 * DocumentService.js
 * Core logic for handling local document storage and metadata within the Vault.
 */

const fs = require('fs-extra');
const path = require('path');

class DocumentService {
    constructor(storagePath) {
        this.storagePath = storagePath || path.join(__dirname, '../../storage/vault');
        this.init();
    }

    async init() {
        try {
            await fs.ensureDir(this.storagePath);
            console.log(`[DocumentService] Storage initialized at: ${this.storagePath}`);
        } catch (error) {
            console.error(`[DocumentService] Failed to initialize storage:`, error);
        }
    }

    /**
     * Store a file and return its metadata
     */
    async storeFile(file, metadata = {}) {
        const targetPath = path.join(this.storagePath, file.originalname);
        await fs.copy(file.path, targetPath);
        
        return {
            name: file.originalname,
            path: targetPath,
            size: file.size,
            mimeType: file.mimetype,
            timestamp: new Date().toISOString(),
            ...metadata
        };
    }

    /**
     * List all documents in the vault
     */
    async listDocuments() {
        const files = await fs.readdir(this.storagePath);
        return files.map(f => ({ name: f }));
    }

    /**
     * Securely purge a document
     */
    async purgeDocument(filename) {
        const filePath = path.join(this.storagePath, filename);
        await fs.remove(filePath);
        return { success: true, purged: filename };
    }
}

module.exports = new DocumentService();
