package com.daviddalb.enterpriseknowledgeengine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daviddalb.enterpriseknowledgeengine.dto.CreateDocumentRequest;
import com.daviddalb.enterpriseknowledgeengine.dto.DocumentResponse;
import com.daviddalb.enterpriseknowledgeengine.mapper.DocumentMapper;
import com.daviddalb.enterpriseknowledgeengine.model.DocumentEntity;
import com.daviddalb.enterpriseknowledgeengine.repository.DocumentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class DocumentService {

	private final DocumentRepository documentRepository;
	private final DocumentMapper documentMapper;
	
	public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
		this.documentRepository = documentRepository;
		this.documentMapper = documentMapper;
	}
	
	@Transactional
	public DocumentResponse createDocument(CreateDocumentRequest request) {
		DocumentEntity entity = documentMapper.toEntity(request);
		DocumentEntity savedEntity = documentRepository.save(entity);
		return documentMapper.toResponse(savedEntity);
		
	}
	public List<DocumentResponse> getAllDocuments() {
		return documentRepository.findAll()
				.stream()
				.map(documentMapper::toResponse)
				.toList();
	}
	
	public DocumentResponse getDocumentById(Long id) {
		return documentRepository.findById(id)
				.map(documentMapper::toResponse)
				.orElseThrow(() -> new EntityNotFoundException("Documento con ID " + id + " non trovato"));
	
	}
	@Transactional
	public DocumentResponse updateDocument(Long id, CreateDocumentRequest request) {
		
		if (id == null || request == null ) {
			throw new IllegalArgumentException("Dati ID e Request non possono essere nulli");
		}
		DocumentEntity entity = documentRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("Documento con ID: " + id + " non trovato"));
		
		entity.setTitle(request.title());
		entity.setContent(request.content());
		entity.setDocumentType(request.documentType());
		entity.setFilename(request.filename());
		entity.setContentType(request.contentType());
		entity.setLanguageCode(request.languageCode());
		
		DocumentEntity entityUpdated = documentRepository.save(entity);
		
		return documentMapper.toResponse(entityUpdated);
	}
	@Transactional
	public void deleteDocument(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("L'ID del documento non può essere nullo");
		}
		if(!documentRepository.existsById(id)) {
			throw new EntityNotFoundException("Documento non trovato");
		}
		documentRepository.deleteById(id);
	}

}
