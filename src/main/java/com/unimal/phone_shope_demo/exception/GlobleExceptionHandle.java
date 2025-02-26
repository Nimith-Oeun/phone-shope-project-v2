package com.unimal.phone_shope_demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandle {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException e) {
        ErrorRespone errorRespone = new ErrorRespone(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(errorRespone);
    }
}
