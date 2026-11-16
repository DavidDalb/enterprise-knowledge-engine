package com.daviddalb.enterpriseknowledgeengine.mapper;

import com.daviddalb.enterpriseknowledgeengine.dto.CreateDocumentRequest;
import com.daviddalb.enterpriseknowledgeengine.dto.DocumentResponse;
import com.daviddalb.enterpriseknowledgeengine.model.DocumentEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uploadDate", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "archived", ignore = true)
	@Mapping(target = "ownerId", ignore = true)
	@Mapping(target = "version", ignore = true)
    DocumentEntity toEntity(CreateDocumentRequest dto);

    DocumentResponse toResponse(DocumentEntity entity);
}
