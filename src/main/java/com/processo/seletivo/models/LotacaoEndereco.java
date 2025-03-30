package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "lotacao_endereco")
public class LotacaoEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lotacao lotacao;

    @ManyToOne
    @JoinColumn(name = "end_id")
    private Endereco endereco;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Lotacao getLotacao() { return lotacao; }
    public void setLotacao(Lotacao lotacao) { this.lotacao = lotacao; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
