package com.library.controller;


import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.ErrorMessage;
import com.library.domain.ErrorMessages;
import com.library.service.AuthorService;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(final BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        if (book == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Book createdBook = this.bookService.addBook(book);

        return new ResponseEntity(createdBook, HttpStatus.CREATED);
    }

    @GetMapping(value = "/authorId/{id}/bookName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity receiveBookByAuthorIdAndBook(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        Author authorById = this.authorService.getAuthorById(id);

        if (authorById == null) {
            ErrorMessage authorIsNotFound = new ErrorMessage(ErrorMessages.AUTHOR_NOT_FOUND.getMessage());
            return new ResponseEntity(authorIsNotFound, HttpStatus.NOT_FOUND);
        }

        Book receivedBook = this.bookService.getBookByAuthorIdAndBookName(id, name);

        if (receivedBook == null) {
            ErrorMessage authorIsNotFound = new ErrorMessage(ErrorMessages.BOOKS_ARE_NOT_FOUND.getMessage());
            return new ResponseEntity(authorIsNotFound, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(receivedBook, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> receiveAllBooks() {
        List<Book> allOfCurrentBooks = this.bookService.getAllBooks();

        if (allOfCurrentBooks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(allOfCurrentBooks, HttpStatus.OK);
    }

    @GetMapping(value = "/authorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity receiveBooksByAuthorId(@PathVariable("id") Integer id) {
        Author authorById = this.authorService.getAuthorById(id);

        if (authorById == null) {
            ErrorMessage authorIsNotFound = new ErrorMessage(ErrorMessages.AUTHOR_NOT_FOUND.getMessage());
            return new ResponseEntity(authorIsNotFound, HttpStatus.NOT_FOUND);
        }

        List<Book> booksByAuthorId = this.bookService.getBooksByAuthorId(authorById.getId());

        if (booksByAuthorId.isEmpty()) {
            ErrorMessage authorIsNotFound = new ErrorMessage(ErrorMessages.BOOKS_ARE_NOT_FOUND.getMessage());
            return new ResponseEntity(authorIsNotFound, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(booksByAuthorId, HttpStatus.OK);
    }
}
