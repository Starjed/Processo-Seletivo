package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
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

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndUf() {
        return endUf;
    }

    public void setEndUf(String endUf) {
        this.endUf = endUf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
