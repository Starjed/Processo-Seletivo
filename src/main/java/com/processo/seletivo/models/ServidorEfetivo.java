package com.processo.seletivo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pesId;

    private String seMatricula;

    @OneToOne
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id")
    private Pessoa pessoa;
}
