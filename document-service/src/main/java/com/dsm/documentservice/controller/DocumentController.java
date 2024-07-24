package com.dsm.documentservice.controller;

import com.dsm.documentservice.common.dto.DocumentDTO;
import com.dsm.documentservice.common.response.ApiResponse;
import com.dsm.documentservice.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadDocument(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.uploadDocument(documentDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<DocumentDTO>>> listDocuments() {
        return ResponseEntity.ok(documentService.listDocuments());
    }
}

