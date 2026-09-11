package com.daviddalb.enterpriseknowledgeengine.mapper;

import com.daviddalb.enterpriseknowledgeengine.dto.CreateDocumentRequest;
import com.daviddalb.enterpriseknowledgeengine.dto.DocumentResponse;
import com.daviddalb.enterpriseknowledgeengine.model.DocumentEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentEntity toEntity(CreateDocumentRequest dto);

    DocumentResponse toResponse(DocumentEntity entity);
}
