package com.dsm.documentservice.service;

import static org.junit.jupiter.api.Assertions.*;


import com.dsm.documentservice.common.dto.DocumentDTO;
import com.dsm.documentservice.common.response.ApiResponse;
import com.dsm.documentservice.model.Document;
import com.dsm.documentservice.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;

public class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadDocument() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentName("Test Document");
        documentDTO.setDocumentContent("Sample content".getBytes());

        Document document = new Document();
        document.setDocumentName("Test Document");
        document.setDocumentContent("Sample content".getBytes());

        when(documentRepository.save(any(Document.class))).thenReturn(document);

        ApiResponse<String> response = documentService.uploadDocument(documentDTO);

        assertTrue(response.isSuccess());
        assertEquals("Document uploaded successfully", response.getMessage());
    }

    @Test
    void testListDocuments() {
        List<Document> documents = new ArrayList<>();
        when(documentRepository.findAll()).thenReturn(documents);

        ApiResponse<List<DocumentDTO>> response = documentService.listDocuments();

        assertTrue(response.isSuccess());
        assertEquals("Documents retrieved successfully", response.getMessage());
        assertNotNull(response.getData());
        assertEquals(0, response.getData().size());
    }
}