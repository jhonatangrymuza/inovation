package com.br.inovation.dto;

import com.br.inovation.models.Pedido;
import lombok.Data;

import javax.persistence.*;

@Data
public class EntregaDTO {

    private Long id;

    private String endereco;

    private Pedido pedido;
}
