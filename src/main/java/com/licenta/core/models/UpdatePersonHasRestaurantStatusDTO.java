package com.licenta.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePersonHasRestaurantStatusDTO {

    private Long id;
    private Boolean status;
}
