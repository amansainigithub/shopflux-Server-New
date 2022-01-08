package com.bezkoder.springjwt.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


public class ResponseData {

    private String message;
    private String status;
    private String code;

    public ResponseData(String message, String status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public ResponseData(String message) {
        this.message = message;
    }

    public ResponseData(String message, String status) {
        this.message = message;
        this.status = status;
    }


}
