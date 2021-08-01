package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "there are no books")
public class BookNotFoundException extends ResponseStatusException {

    public BookNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
