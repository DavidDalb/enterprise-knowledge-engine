package com.daviddalb.enterpriseknowledgeengine.dto;

import com.daviddalb.enterpriseknowledgeengine.model.DocumentType;
import java.time.LocalDateTime;

public record DocumentResponse(
    Long id,
    String title,
    String content,
    DocumentType documentType,
    String filename,
    String contentType,
    LocalDateTime uploadDate,
    String status,
    String languageCode,
    boolean archived,
    String ownerId,
    Long version,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    }

