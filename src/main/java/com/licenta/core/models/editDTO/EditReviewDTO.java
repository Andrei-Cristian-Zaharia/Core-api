package com.licenta.core.models.editDTO;

import lombok.Data;

@Data
public class EditReviewDTO {

    private Long id;
    private String title;
    private Integer rating;
    private String text;
}
