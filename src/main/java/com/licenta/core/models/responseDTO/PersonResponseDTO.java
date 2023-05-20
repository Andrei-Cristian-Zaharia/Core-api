package com.licenta.core.models.responseDTO;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonResponseDTO {

    private Long id;
    private String username;
    private String emailAddress;
    private Boolean hasRestaurant;
    private String accountType;
}
