package com.library.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true)
public class Author {

    private Integer id;
    private String name;
    private String lastName;
    private Country countryOfBirth;
}
