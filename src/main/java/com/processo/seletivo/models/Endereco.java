package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endId;

    private String endLogradouro;
    private String endBairro;
    private String endCidade;
    private String endUf;
    private String endCep;

}
