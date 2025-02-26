package com.unimal.phone_shope_demo.exception;

import org.springframework.http.HttpStatus;

public class ResoureNoteFoundException extends ApiException{
    public ResoureNoteFoundException(String resoureName , Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d", resoureName,id));

    }
}
