package com.library.domain;

import lombok.Getter;

@Getter
public enum Language {
    RUSSIAN("Russian"), ENGLISH("English"), DEUTSCH("Deutsch");

    private String name;

    Language(String name) {
        this.name = name;
    }

}
