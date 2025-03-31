package com.processo.seletivo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lotacao_endereco")
@Getter
@Setter
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
}
