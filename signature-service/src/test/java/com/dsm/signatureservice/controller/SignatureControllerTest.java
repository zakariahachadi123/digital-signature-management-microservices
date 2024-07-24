package com.dsm.signatureservice.controller;


import com.dsm.signatureservice.common.dto.SignatureDTO;
import com.dsm.signatureservice.common.response.ApiResponse;
import com.dsm.signatureservice.service.SignatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class SignatureControllerTest {

    @InjectMocks
    private SignatureController signatureController;

    @Mock
    private SignatureService signatureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignDocument() {
        SignatureDTO signatureDTO = new SignatureDTO();
        signatureDTO.setDocumentId("1");
        signatureDTO.setSignature("Sample signature");

        when(signatureService.signDocument(signatureDTO)).thenReturn(new ApiResponse<>(true, "Document signed successfully", null));

        ResponseEntity<ApiResponse<String>> response = signatureController.signDocument(signatureDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Document signed successfully", response.getBody().getMessage());
    }

    @Test
    void testVerifySignature() {
        SignatureDTO signatureDTO = new SignatureDTO();
        signatureDTO.setDocumentId("1");
        signatureDTO.setSignature("Sample signature");

        when(signatureService.verifySignature(signatureDTO)).thenReturn(new ApiResponse<>(true, "Signature verification result", true));

        ResponseEntity<ApiResponse<Boolean>> response = signatureController.verifySignature(signatureDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Signature verification result", response.getBody().getMessage());
        assertTrue(response.getBody().getData());
    }
}