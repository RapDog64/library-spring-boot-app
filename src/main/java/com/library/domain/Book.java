package com.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "language")
    private String language;

    @JsonProperty(value = "authorId")
    private int authorId;
}
