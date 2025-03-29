package com.processo.seletivo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
public class FotoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fpId;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    private LocalDate fpData;
    private String fpBucket;
    private String fpHash;
}
