package com.ccsw.ccswmanager.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Hay un usuario que esta vinculado a este Centro Educativo")
public class InternInUseException extends Exception {

    public InternInUseException(String message) {
        super(message);
    }
    public InternInUseException() {
    }
}
