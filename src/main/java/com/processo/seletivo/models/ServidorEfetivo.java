package com.processo.seletivo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {

    @Id
    private Long pesId;

    private String seMatricula;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "servidorEfetivo")
    private List<Lotacao> lotacoes;



    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
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
