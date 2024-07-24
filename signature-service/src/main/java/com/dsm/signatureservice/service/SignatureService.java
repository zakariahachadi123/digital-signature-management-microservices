package com.dsm.signatureservice.service;

import com.dsm.signatureservice.common.dto.SignatureDTO;
import com.dsm.signatureservice.common.response.ApiResponse;
import com.dsm.signatureservice.model.Signature;
import com.dsm.signatureservice.repository.SignatureRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SignatureService {

    private final SignatureRepository signatureRepository;

    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }

    public ApiResponse<String> signDocument(SignatureDTO signatureDTO) {
        Signature signature = new Signature();
        signature.setDocumentId(signatureDTO.getDocumentId());
        signature.setSignature(Base64.getEncoder().encodeToString(signatureDTO.getSignature().getBytes()));
        signatureRepository.save(signature);

        return new ApiResponse<>(true, "Document signed successfully", " Signature : "+signature.getSignature());
    }

    public ApiResponse<Boolean> verifySignature(SignatureDTO signatureDTO) {
        Signature signature = signatureRepository.findByDocumentId(signatureDTO.getDocumentId());
        if (signature == null) {
            return new ApiResponse<>(false, "Document not found", false);
        }

        String decodedSignature = new String(Base64.getDecoder().decode(signature.getSignature()));
        boolean isValid = decodedSignature.equals(signatureDTO.getSignature());

        return new ApiResponse<>(true, "Signature verification result", isValid);
    }
}
