package com.br.inovation.models;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

@Data
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Transient
    private List<Pedido> pedidos;
}
