package com.daviddalb.enterpriseknowledgeengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String content;

    @Column(name = "document_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "language_code", length = 5)
    private String languageCode = "en";

    @Column(name = "is_archived", nullable = false)
    private boolean archived = false;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @Column(name = "status", nullable = false)
    private String status = "DRAFT";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Version
    private Long version;

    // Costruttore vuoto per JPA
    public DocumentEntity() {
    }

    // Costruttore con parametri
    public DocumentEntity(String title, String content, DocumentType documentType, String languageCode, boolean archived, 
                        String ownerId, String filename, String contentType, LocalDateTime uploadDate, String status) {
        this.title = title;
        this.content = content;
        this.documentType = documentType;
        this.languageCode = languageCode;
        this.archived = archived;
        this.ownerId = ownerId;
        this.filename = filename;
        this.contentType = contentType;
        this.uploadDate = uploadDate;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        this.updatedAt = LocalDateTime.now();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
        this.updatedAt = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
        this.updatedAt = LocalDateTime.now();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentEntity that = (DocumentEntity) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(title, that.title) && 
               Objects.equals(content, that.content) && 
               Objects.equals(documentType, that.documentType) &&
               Objects.equals(filename, that.filename) &&
               Objects.equals(contentType, that.contentType) &&
               Objects.equals(uploadDate, that.uploadDate) &&
               Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, documentType, filename, contentType, uploadDate, status);
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", documentType=" + documentType +
               ", languageCode='" + languageCode + '\'' +
               ", archived=" + archived +
               ", ownerId='" + ownerId + '\'' +
               ", filename='" + filename + '\'' +
               ", contentType='" + contentType + '\'' +
               ", uploadDate=" + uploadDate +
               ", status='" + status + '\'' +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               ", version=" + version +
               '}';
    }
}
