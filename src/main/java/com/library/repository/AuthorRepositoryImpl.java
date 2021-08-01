package com.library.repository;

import com.library.domain.Author;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public void addAuthor(Author author) {
        CustomDatabase.getInstance().getAuthorStorage().add(author);
    }

    @Override
    public Author getAuthorById(Integer id) {
        for (Author author : CustomDatabase.getInstance().getAuthorStorage()) {
            if (author.getId().equals(id)) {
                return Optional.of(author).get();
            }
        }
        return null;
    }
}
