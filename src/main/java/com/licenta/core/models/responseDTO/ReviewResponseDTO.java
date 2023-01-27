package com.licenta.core.models.responseDTO;

import com.licenta.core.models.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReviewResponseDTO {

    private Long id;

    private String title;

    private String text;

    private LocalDate creationDate;

    private Integer rating;

    private String category;

    private PersonResponseDTO person;
}
