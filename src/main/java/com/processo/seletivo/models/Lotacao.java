package com.processo.seletivo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
public class Lotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lotId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private ServidorEfetivo servidorEfetivo;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private Unidade unidade;

    private LocalDate lotDataLotacao;
    private LocalDate lotDataRemocao;

    // Getters e Setters
}

