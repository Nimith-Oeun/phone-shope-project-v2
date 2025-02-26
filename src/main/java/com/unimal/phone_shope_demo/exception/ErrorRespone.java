package com.unimal.phone_shope_demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorRespone {
    private HttpStatus status;
    private String message;
}
