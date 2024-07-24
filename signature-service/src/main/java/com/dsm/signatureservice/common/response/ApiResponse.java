package com.dsm.signatureservice.common.response;

import lombok.Data;

import java.io.Serializable;


@Data
public class ApiResponse<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


}
