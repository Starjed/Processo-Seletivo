package com.processo.seletivo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private String endTipoLogradouro;
    private String endLogradouro;
    private Integer endNumero;
    private String endBairro;
    private String endCep;
    private String endUf;
    private Integer cidadeId;
}