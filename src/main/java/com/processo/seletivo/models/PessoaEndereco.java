package com.processo.seletivo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa_endereco")
@Getter
@Setter
public class PessoaEndereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Getter
    @ManyToOne
    @JoinColumn(name = "end_id")
    private Endereco endereco;

}

