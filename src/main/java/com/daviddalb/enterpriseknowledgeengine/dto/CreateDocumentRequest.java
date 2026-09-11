package com.daviddalb.enterpriseknowledgeengine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.daviddalb.enterpriseknowledgeengine.model.DocumentType;

public record CreateDocumentRequest(
	    @NotBlank(message = "Title is required")
	    @Size(max = 255, message = "Title cannot exceed 255 characters")
	    String title,

	    @NotBlank(message = "Content is required")
	    String content,

	    @NotNull(message = "Document type is required")
	    DocumentType documentType,

	    @NotBlank(message = "Filename is required")
	    @Size(max = 255, message = "Filename cannot exceed 255 characters")
	    String filename,

	    @NotBlank(message = "Content type is required")
	    @Size(max = 100, message = "Content type cannot exceed 100 characters")
	    String contentType,

	    @NotBlank(message = "Language code is required")
	    @Size(min = 2, max = 5, message = "Language code must be between 2 and 5 characters")
	    String languageCode
	)
{
	
}






