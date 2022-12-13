package com.licenta.core.models;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmAccountDTO {

    @Valid
    private String emailAddress;

    @Valid
    private String password;
}
