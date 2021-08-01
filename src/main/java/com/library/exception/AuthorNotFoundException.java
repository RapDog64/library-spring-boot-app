package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthorNotFoundException extends ResponseStatusException {


    public AuthorNotFoundException(HttpStatus status) {
        super(status);
    }

    public AuthorNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public AuthorNotFoundException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public AuthorNotFoundException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
