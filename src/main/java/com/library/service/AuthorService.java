package com.library.service;

import com.library.domain.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.getAuthorById(id);
    }

    public Author addAuthor(Author author) {
        Author createdAuthor = Author.builder()
                .id(author.getId())
                .name(author.getName())
                .lastName(author.getLastName())
                .countryOfBirth(author.getCountryOfBirth())
                .build();

         authorRepository.addAuthor(createdAuthor);

        return createdAuthor;
    }


}
