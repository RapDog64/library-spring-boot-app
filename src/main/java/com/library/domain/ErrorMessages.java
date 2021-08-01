package com.library.domain;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    AUTHOR_NOT_FOUND("The author is not found"), BOOKS_ARE_NOT_FOUND("Books are not found");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
