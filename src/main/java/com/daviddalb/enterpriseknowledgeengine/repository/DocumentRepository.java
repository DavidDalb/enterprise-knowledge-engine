package com.daviddalb.enterpriseknowledgeengine.repository;

import com.daviddalb.enterpriseknowledgeengine.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    //Trova i documenti per stato
    List<DocumentEntity> findByStatus(String status);

    //Verifica se esiste un documento con il nome file specificato
    boolean existsByFilename(String filename);
}
