package com.br.inovation.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
