package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cidId;

    @Column(length = 200)
    private String cidNome;

    @Column(length = 2)
    private String cidUf;

    public Integer getCidId() {
        return cidId;
    }

    public void setCidId(Integer cidId) {
        this.cidId = cidId;
    }

    public String getCidNome() {
        return cidNome;
    }

    public void setCidNome(String cidNome) {
        this.cidNome = cidNome;
    }

    public String getCidUf() {
        return cidUf;
    }

    public void setCidUf(String cidUf) {
        this.cidUf = cidUf;
    }
}
