package com.processo.seletivo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lotId;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private ServidorEfetivo servidorEfetivo;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;

    @Column(name = "lot_data_lotacao")
    private LocalDate lotDataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate lotDataRemocao;

    @Column(length = 100)
    private String lotPortaria;

    public Integer getLotId() {
        return lotId;
    }

    public void setLotId(Integer lotId) {
        this.lotId = lotId;
    }

    public ServidorEfetivo getServidorEfetivo() {
        return servidorEfetivo;
    }

    public void setServidorEfetivo(ServidorEfetivo servidorEfetivo) {
        this.servidorEfetivo = servidorEfetivo;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public LocalDate getLotDataLotacao() {
        return lotDataLotacao;
    }

    public void setLotDataLotacao(LocalDate lotDataLotacao) {
        this.lotDataLotacao = lotDataLotacao;
    }

    public LocalDate getLotDataRemocao() {
        return lotDataRemocao;
    }

    public void setLotDataRemocao(LocalDate lotDataRemocao) {
        this.lotDataRemocao = lotDataRemocao;
    }

    public String getLotPortaria() {
        return lotPortaria;
    }

    public void setLotPortaria(String lotPortaria) {
        this.lotPortaria = lotPortaria;
    }
}
