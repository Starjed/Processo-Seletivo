package com.processo.seletivo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lotacao")
@Getter
@Setter
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lotId;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    @JsonBackReference
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

    @OneToMany(mappedBy = "lotacao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LotacaoEndereco> enderecos;
}
