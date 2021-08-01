package com.library.repository;

import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Country;

import java.util.ArrayList;
import java.util.List;

public class CustomDatabase {

    private static CustomDatabase instance = null;
    private static List<Book> bookStorage = new ArrayList<>();
    private static List<Author> authorStorage = new ArrayList<>();

    private CustomDatabase() {
    }

    public static synchronized CustomDatabase getInstance() {
        if (instance == null) {
            instance = new CustomDatabase();
            addStartBooks();
        }
        return instance;
    }

    public List<Book> getBookStorage() {
        return bookStorage;
    }

    public List<Author> getAuthorStorage() {
        return authorStorage;
    }

    private static void addStartBooks() {
        authorStorage.add(new Author(190, "Dima", "Ivanov", Country.RU));
        authorStorage.add(new Author(200, "Ivan", "Ivanov", Country.USA));
    }
}
