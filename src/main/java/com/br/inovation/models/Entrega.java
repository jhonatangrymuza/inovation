package com.br.inovation.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String endereco;

    @OneToOne
    private Pedido pedido;
}
