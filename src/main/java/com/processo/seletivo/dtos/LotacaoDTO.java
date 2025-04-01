package com.processo.seletivo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LotacaoDTO {
    private Integer servidorId;
    private Integer unidadeId;
    private LocalDate lotDataLotacao;
    private LocalDate lotDataRemocao;
    private String lotPortaria;
}
