package com.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class ErrorMessage {

    @JsonProperty(value = "error_message")
    private String errorMessage;
}
