package com.processo.seletivo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {
    private String pesNome;
    private LocalDate pesDataNascimento;
    private String pesSexo;
    private String pesMae;
    private String pesPai;
}
