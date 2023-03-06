package com.licenta.core.models.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseRecipeDTO {

    private Long id;
    private int time;
    private int difficulty;
    private int spiciness;
    private boolean isVegan;
    private String imageAddress;
}
