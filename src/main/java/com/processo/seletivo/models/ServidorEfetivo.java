package com.processo.seletivo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore
    private Pessoa pessoa;

    @OneToMany(mappedBy = "servidorEfetivo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Lotacao> lotacoes = new ArrayList<>();
}
