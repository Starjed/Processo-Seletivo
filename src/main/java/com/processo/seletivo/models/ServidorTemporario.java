package com.processo.seletivo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporario {

    @Id
    private Integer pesId;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "st_data_admissao")
    private LocalDate stDataAdmissao;

    @Column(name = "st_data_demissao")
    private LocalDate stDataDemissao;

    public Integer getPesId() {
        return pesId;
    }

    public void setPesId(Integer pesId) {
        this.pesId = pesId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public void setStDataAdmissao(LocalDate stDataAdmissao) {
        this.stDataAdmissao = stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    public void setStDataDemissao(LocalDate stDataDemissao) {
        this.stDataDemissao = stDataDemissao;
    }
}
