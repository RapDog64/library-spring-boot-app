package com.library.repository;

import com.library.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class BookRepositoryImpl implements BookRepository {


    @Override
    public void addBook(Book book) {
        CustomDatabase.getInstance().getBookStorage().add(book);
    }

    @Override
    public Book getBookByAuthorIdAndBookName(Integer id, String bookName) {
        for (Book book : CustomDatabase.getInstance().getBookStorage()) {
            if (book.getAuthorId() == id && book.getName().equals(bookName)) {
                return Optional.of(book).get();
            }
        }
        return null;
    }

    @Override
    public List<Book> getBooksByAuthorId(Integer id) {
        return CustomDatabase.getInstance().getBookStorage().stream()
                .filter(book -> book.getAuthorId() == id)
                .collect(toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return CustomDatabase.getInstance().getBookStorage();
    }

    @Override
    public void removeAll() {
        CustomDatabase.getInstance().getBookStorage().clear();
    }
}
