package com.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class Book {

    @ApiModelProperty(notes = "Book id")
    @JsonProperty(value = "id")
    private int id;

    @ApiModelProperty(notes = "Name of the book")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(notes = "Description of the book")
    @JsonProperty(value = "description")
    private String description;

    @ApiModelProperty(notes = "Language of the book")
    @JsonProperty(value = "language")
    private String language;

    @ApiModelProperty(notes = "Author id")
    @JsonProperty(value = "authorId")
    private int authorId;
}
