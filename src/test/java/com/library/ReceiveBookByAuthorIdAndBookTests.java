package com.library;

import com.library.domain.Book;
import com.library.domain.ErrorMessage;
import com.library.domain.ErrorMessages;
import com.library.domain.Language;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ReceiveBookByAuthorIdAndBookTests extends BaseRest {

    @Test
    @Tag("api")
    void findBookWithValidData() {
        final int authorId = 200;
        Book generatedBook = Book.builder()
                .id(faker.number().numberBetween(1, 99))
                .authorId(authorId)
                .name(faker.harryPotter().book())
                .description(faker.harryPotter().quote())
                .language(Language.RUSSIAN.getName())
                .build();

        Book book = createBook(generatedBook);
        Book actualBook = findByAuthorIdAndBookName(authorId, book.getName(), 200).as(Book.class);

        assertThat(actualBook).isEqualTo(generatedBook);
    }

    @Test
    @Tag("api")
    void findBookWithInvalidAuthorId() {
        final int id = faker.number().numberBetween(1, 99);
        final String book = StringUtils.deleteWhitespace(faker.harryPotter().book());

        ErrorMessage notFoundAuthor = findByAuthorIdAndBookName(id, book, 404).as(ErrorMessage.class);

        assertThat(notFoundAuthor.getErrorMessage()).isEqualTo(ErrorMessages.AUTHOR_NOT_FOUND.getMessage());
    }

    @Test
    @Tag("api")
    void findBookWithValidAuthorAndInvalidBookName() {
        final int id = 190;
        final String book = StringUtils.deleteWhitespace(faker.harryPotter().book());

        ErrorMessage notFoundAuthor = findByAuthorIdAndBookName(id, book, 404).as(ErrorMessage.class);

        assertThat(notFoundAuthor.getErrorMessage()).isEqualTo(ErrorMessages.BOOKS_ARE_NOT_FOUND.getMessage());
    }

    @Test
    @Tag("api")
    void findAllBooksByAuthorId() {
        final int authorId = 200;

        List<Book> bookList = prepareBooksForAuthor(authorId);


        List<Book> books = given()
                .spec(REQUEST_SPEC_BUILDER)
                .when()
                .get("books/authorId/" + authorId)
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .statusCode(200)
                .extract().jsonPath().getList(".", Book.class);

        assertThat(books).isNotEmpty();
        assertThat(books.containsAll(bookList)).isTrue();
    }

    private List<Book> prepareBooksForAuthor(int authorId) {
        Book newBook = Book.builder()
                .id(faker.number().numberBetween(1, 99))
                .authorId(authorId)
                .name(faker.lordOfTheRings().character())
                .description(faker.lordOfTheRings().location())
                .language(Language.RUSSIAN.getName())
                .build();

        Book gameOfThrones = Book.builder()
                .id(faker.number().numberBetween(1, 99))
                .authorId(authorId)
                .name(faker.gameOfThrones().city())
                .description(faker.gameOfThrones().quote())
                .language(Language.ENGLISH.getName())
                .build();

        List<Book> bookList = new ArrayList<>();

        bookList.add(newBook);
        bookList.add(gameOfThrones);
        bookList.forEach(this::createBook);

        return bookList;
    }


    private Response findByAuthorIdAndBookName(int id, String book, int statusCode) {
        return given()
                .spec(REQUEST_SPEC_BUILDER)
                .when()
                .get("books/authorId/" + id + "/bookName/" + book)
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .statusCode(statusCode)
                .extract()
                .response();
    }
}
