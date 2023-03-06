package com.licenta.core.models.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RestaurantDTO {

    private String name;

    private String description;

    private String address;

    private LocalDate partnerSince;
}
