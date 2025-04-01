package com.processo.seletivo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServidorTemporarioDTO {
    private Integer pessoaId;
    private LocalDate stDataAdmissao;
    private LocalDate stDataDemissao;
}