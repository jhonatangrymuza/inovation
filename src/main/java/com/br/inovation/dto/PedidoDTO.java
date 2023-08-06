package com.br.inovation.dto;

import com.br.inovation.models.Cliente;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PedidoDTO {

    private Long id;

    @NotBlank
    private String descricao;

    @NotBlank
    private Cliente cliente;
}
