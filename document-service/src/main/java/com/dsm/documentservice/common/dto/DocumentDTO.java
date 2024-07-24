package com.dsm.documentservice.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentDTO implements Serializable {
    private String documentName;
    private byte[] documentContent;

    // Getters and Setters
}
