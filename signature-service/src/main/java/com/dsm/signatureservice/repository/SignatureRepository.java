package com.dsm.signatureservice.repository;

import com.dsm.signatureservice.model.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature, Long> {
    Signature findByDocumentId(String documentId);
}
