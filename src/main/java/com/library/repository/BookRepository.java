package com.library.repository;

import com.library.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository {

    void addBook(Book book);
    Book getBookByAuthorIdAndBookName(Integer id, String bookName);
    List<Book> getBooksByAuthorId(Integer id);
    List<Book> getAllBooks();

    void removeAll();
}
