package com.licenta.core.models.editDTO;

import lombok.Data;

@Data
public class EditPersonDTO {

    private Long id;

    private String username;

    private String emailAddress;

    private String accountType;
}
