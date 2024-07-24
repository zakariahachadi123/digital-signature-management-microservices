package com.dsm.documentservice.service;

import com.dsm.documentservice.common.dto.DocumentDTO;
import com.dsm.documentservice.common.response.ApiResponse;
import com.dsm.documentservice.model.Document;
import com.dsm.documentservice.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public ApiResponse<String> uploadDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setDocumentName(documentDTO.getDocumentName());
        document.setDocumentContent(documentDTO.getDocumentContent());
        documentRepository.save(document);

        return new ApiResponse<>(true, "Document uploaded successfully", documentDTO.getDocumentName());
    }

    public ApiResponse<List<DocumentDTO>> listDocuments() {
        List<DocumentDTO> documents = documentRepository.findAll().stream()
                .map(doc -> {
                    DocumentDTO dto = new DocumentDTO();
                    dto.setDocumentName(doc.getDocumentName());
                    dto.setDocumentContent(doc.getDocumentContent());
                    return dto;
                })
                .collect(Collectors.toList());

        return new ApiResponse<>(true, "Documents retrieved successfully", documents);
    }

}
