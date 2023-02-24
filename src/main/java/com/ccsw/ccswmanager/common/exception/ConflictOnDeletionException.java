package com.ccsw.ccswmanager.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictOnDeletionException extends Exception {

    public ConflictOnDeletionException() {

    }

    public ConflictOnDeletionException(String message) {
        super(message);
    }

}
