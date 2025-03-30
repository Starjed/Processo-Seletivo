package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unidId;

    @Column(length = 200, nullable = false)
    private String unidNome;

    @Column(length = 20)
    private String unidSigla;

    public Integer getUnidId() {
        return unidId;
    }

    public void setUnidId(Integer unidId) {
        this.unidId = unidId;
    }

    public String getUnidNome() {
        return unidNome;
    }

    public void setUnidNome(String unidNome) {
        this.unidNome = unidNome;
    }

    public String getUnidSigla() {
        return unidSigla;
    }

    public void setUnidSigla(String unidSigla) {
        this.unidSigla = unidSigla;
    }
}
