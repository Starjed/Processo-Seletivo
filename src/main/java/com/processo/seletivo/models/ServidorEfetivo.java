package com.processo.seletivo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servidor_efetivo")
@Getter
@Setter
public class ServidorEfetivo {

    @Id
    private Integer pesId;

    @Column(length = 20, name = "se_matricula")
    private String seMatricula;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;
}
