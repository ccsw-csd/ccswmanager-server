package com.ccsw.ccswmanager.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "El elemento está siendo usado por otro registro")
public class ItemInUseException extends Exception {

    private static final long serialVersionUID = 1L;

    public ItemInUseException() {
        super();
    }

    public ItemInUseException(String message) {
        super(message);
    }
}