package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        Book newBook = Book.builder()
                .id(book.getId())
                .authorId(book.getAuthorId())
                .description(book.getDescription())
                .name(book.getName())
                .language(book.getLanguage())
                .build();

        bookRepository.addBook(newBook);

        return newBook;
    }

    public Book getBookByAuthorIdAndBookName(Integer id, String bookName) {
        return bookRepository.getBookByAuthorIdAndBookName(id, bookName);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> getBooksByAuthorId(Integer id) {
        return bookRepository.getBooksByAuthorId(id);
    }

    public void removeAll() {
        bookRepository.removeAll();
    }
}
