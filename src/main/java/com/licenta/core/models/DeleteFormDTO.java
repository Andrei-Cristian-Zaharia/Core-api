package com.licenta.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteFormDTO {

    private String emailAddress;
    private String password;
}
