package com.dsm.signatureservice.controller;

import com.dsm.signatureservice.common.dto.SignatureDTO;
import com.dsm.signatureservice.common.response.ApiResponse;
import com.dsm.signatureservice.service.SignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signatures")
public class SignatureController {

    private final SignatureService signatureService;

    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @PostMapping("/sign")
    public ResponseEntity<ApiResponse<String>> signDocument(@RequestBody SignatureDTO signatureDTO) {
        return ResponseEntity.ok(signatureService.signDocument(signatureDTO));
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Boolean>> verifySignature(@RequestBody SignatureDTO signatureDTO) {
        return ResponseEntity.ok(signatureService.verifySignature(signatureDTO));
    }
}
