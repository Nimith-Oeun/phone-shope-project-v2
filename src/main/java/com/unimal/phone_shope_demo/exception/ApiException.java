package com.unimal.phone_shope_demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {
   private final HttpStatus status;
   private final String message;
}
