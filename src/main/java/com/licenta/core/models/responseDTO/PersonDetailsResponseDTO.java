package com.licenta.core.models.responseDTO;

import com.licenta.core.models.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PersonDetailsResponseDTO {

    private String username;
    private String emailAddress;
    private Boolean hasRestaurant;
    private String accountType;
    private List<ResponseRecipeDTO> ownedRecipes;
}
