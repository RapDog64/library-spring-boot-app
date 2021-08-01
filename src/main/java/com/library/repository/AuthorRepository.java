package com.library.repository;

import com.library.domain.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository {

    void addAuthor(Author author);
    Author getAuthorById(Integer name);
}
