package com.br.inovation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ClienteDTO {

    @NotBlank
    private String nome;

}
