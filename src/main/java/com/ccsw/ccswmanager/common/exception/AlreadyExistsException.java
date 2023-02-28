package com.ccsw.ccswmanager.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistsException extends Exception {

    public AlreadyExistsException() {

    }

    public AlreadyExistsException(String message) {
        super(message);
    }

}