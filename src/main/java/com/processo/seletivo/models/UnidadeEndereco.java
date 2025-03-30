package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "end_id", nullable = false)
    private Endereco endereco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
