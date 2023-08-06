package com.br.inovation.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Cliente cliente;
}
