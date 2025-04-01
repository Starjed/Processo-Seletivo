package com.processo.seletivo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "unidade")
@Getter
@Setter
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unidId;

    @Column(length = 200, nullable = false)
    private String unidNome;

    @Column(length = 20)
    private String unidSigla;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UnidadeEndereco> enderecos;
}
