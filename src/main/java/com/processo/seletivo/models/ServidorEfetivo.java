package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {

    @Id
    private Integer pesId;

    @Column(length = 20, name = "se_matricula")
    private String seMatricula;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;


    public Integer getPesId() {
        return pesId;
    }

    public void setPesId(Integer pesId) {
        this.pesId = pesId;
    }

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
