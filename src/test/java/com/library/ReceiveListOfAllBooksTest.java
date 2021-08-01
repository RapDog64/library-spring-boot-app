package com.library;

import com.library.domain.Book;
import com.library.domain.Language;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ReceiveListOfAllBooksTest extends BaseRest {

    @Test
    @Tag("api")
    void receiveListOfAllBooksTest() {
        List<Book> bookList = prepareBooks();

        List<Book> books = given()
                .spec(REQUEST_SPEC_BUILDER)
                .when()
                .get("/books")
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .statusCode(200)
                .extract().jsonPath().getList(".", Book.class);


        assertThat(books).isNotEmpty();
        assertThat(books.containsAll(bookList)).isTrue();
    }


    private  List<Book> prepareBooks() {
        Book newBook = Book.builder()
                .id(faker.number().numberBetween(1, 99))
                .authorId(faker.number().numberBetween(1, 99))
                .name(faker.lordOfTheRings().character())
                .description(faker.lordOfTheRings().location())
                .language(Language.RUSSIAN.getName())
                .build();

        Book gameOfThrones = Book.builder()
                .id(faker.number().numberBetween(1, 99))
                .authorId(faker.number().numberBetween(1, 99))
                .name(faker.gameOfThrones().city())
                .description(faker.gameOfThrones().quote())
                .language(Language.ENGLISH.getName())
                .build();

        List<Book> bookList = new ArrayList();
        bookList.add(newBook);
        bookList.add(gameOfThrones);
        bookList.forEach(this::createBook);
        return bookList;
    }
}
