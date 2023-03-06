package com.licenta.core.models.createRequestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateReviewDTO {

    private String title;

    private String text;

    private Integer rating;

    private String category;

    private String ownerEmail;

    private Long recipeId;

    private Long restaurantId;
}
