package com.library;

import com.github.javafaker.Faker;
import com.library.domain.Book;
import com.library.service.BookService;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.*;

abstract class BaseRest {

    protected static Faker faker = new Faker();

    protected static final RequestSpecification REQUEST_SPEC_BUILDER = with()
            .baseUri("http://localhost:9090")
            .basePath("/api/v1")
            .log().body()
            .contentType(ContentType.JSON);

    protected static final ResponseSpecification RESPONSE_SPECIFICATION = expect()
            .contentType(ContentType.JSON)
            .log().body();

    protected Book createBook(Book book) {
        return given()
                .spec(REQUEST_SPEC_BUILDER)
                .body(book)
                .when()
                .post("books")
                .then()
                .spec(RESPONSE_SPECIFICATION)
                .statusCode(201)
                .extract()
                .response()
                .as(Book.class);
    }

}
