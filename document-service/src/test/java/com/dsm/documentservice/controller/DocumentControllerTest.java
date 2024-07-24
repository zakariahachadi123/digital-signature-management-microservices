package com.dsm.documentservice.controller;


import com.dsm.documentservice.common.dto.DocumentDTO;
import com.dsm.documentservice.common.response.ApiResponse;
import com.dsm.documentservice.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DocumentControllerTest {

    @InjectMocks
    private DocumentController documentController;

    @Mock
    private DocumentService documentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadDocument() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentName("Test Document");
        documentDTO.setDocumentContent("Sample content".getBytes());

        when(documentService.uploadDocument(documentDTO)).thenReturn(new ApiResponse<>(true, "Document uploaded successfully", null));

        ResponseEntity<ApiResponse<String>> response = documentController.uploadDocument(documentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Document uploaded successfully", response.getBody().getMessage());
    }

    @Test
    void testListDocuments() {
        List<DocumentDTO> documents = new ArrayList<>();
        when(documentService.listDocuments()).thenReturn(new ApiResponse<>(true, "Documents retrieved successfully", documents));

        ResponseEntity<ApiResponse<List<DocumentDTO>>> response = documentController.listDocuments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Documents retrieved successfully", response.getBody().getMessage());
        assertEquals(documents, response.getBody().getData());
    }
}