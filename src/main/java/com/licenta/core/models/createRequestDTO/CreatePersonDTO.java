package com.licenta.core.models.createRequestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePersonDTO {

    private String username;
    private String password;
    private String emailAddress;
}
