package com.project.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionByAnnotation extends ResourceNotFoundException {
    public ResourceNotFoundExceptionByAnnotation() {
    }

    public ResourceNotFoundExceptionByAnnotation(String message) {
        super(message);
    }
}
