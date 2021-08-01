package com.library;

import com.library.domain.Book;
import com.library.domain.Language;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CreateBookTests extends BaseRest {

    @Test
    @Tag("api")
    void creteBookWithValidData() {
        Book expectedBook = Book.builder()
                .id(faker.number().numberBetween(1, 99))
                .authorId(faker.number().numberBetween(1, 99))
                .name(faker.harryPotter().book())
                .description(faker.harryPotter().quote())
                .language(Language.RUSSIAN.getName())
                .build();

        Book actualBook = createBook(expectedBook);

        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    @Tag("api")
    void creteBookWithNull() {
        given()
                .spec(REQUEST_SPEC_BUILDER)
        .when()
                .post("books")
        .then()
                .spec(RESPONSE_SPECIFICATION)
                .statusCode(400);
    }
}
