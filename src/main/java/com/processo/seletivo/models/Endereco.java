package com.processo.seletivo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer endId;

    @Column(length = 50)
    private String endTipoLogradouro;

    @Column(length = 200)
    private String endLogradouro;

    private Integer endNumero;

    @Column(length = 100)
    private String endBairro;

    @Column(length = 9)
    private String endCep;

    @Column(length = 2)
    private String endUf;

    @ManyToOne
    @JoinColumn(name = "cid_id", nullable = false)
    private Cidade cidade;

    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaEndereco> pessoas;

    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnidadeEndereco> unidades;

    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LotacaoEndereco> lotacoes;
}
