package com.dsm.signatureservice.common.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class SignatureDTO implements Serializable {
    private String documentId;
    private String signature;

    // Getters and Setters
}

