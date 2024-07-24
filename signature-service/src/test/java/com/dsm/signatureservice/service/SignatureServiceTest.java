package com.dsm.signatureservice.service;


import com.dsm.signatureservice.common.dto.SignatureDTO;
import com.dsm.signatureservice.common.response.ApiResponse;
import com.dsm.signatureservice.model.Signature;
import com.dsm.signatureservice.repository.SignatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class SignatureServiceTest {
    @InjectMocks
    private SignatureService signatureService;

    @Mock
    private SignatureRepository signatureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignDocument() {
        SignatureDTO signatureDTO = new SignatureDTO();
        signatureDTO.setDocumentId("1");
        signatureDTO.setSignature("Sample signature");

        Signature signature = new Signature();
        signature.setDocumentId("1");
        signature.setSignature(Base64.getEncoder().encodeToString("Sample signature".getBytes()));

        when(signatureRepository.save(any(Signature.class))).thenReturn(signature);

        ApiResponse<String> response = signatureService.signDocument(signatureDTO);

        assertTrue(response.isSuccess());
        assertEquals("Document signed successfully", response.getMessage());
    }

    @Test
    void testVerifySignature() {
        SignatureDTO signatureDTO = new SignatureDTO();
        signatureDTO.setDocumentId("1");
        signatureDTO.setSignature("Sample signature");

        Signature signature = new Signature();
        signature.setDocumentId("1");
        signature.setSignature(Base64.getEncoder().encodeToString("Sample signature".getBytes()));

        when(signatureRepository.findByDocumentId("1")).thenReturn(signature);

        ApiResponse<Boolean> response = signatureService.verifySignature(signatureDTO);

        assertTrue(response.isSuccess());
        assertEquals("Signature verification result", response.getMessage());
        assertTrue(response.getData());
    }
}