package com.processo.seletivo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoFuncionalDTO {
    private String servidor;
    private String unidade;
    private String endereco;

    public EnderecoFuncionalDTO(String servidor, String unidade, String endereco) {
        this.servidor = servidor;
        this.unidade = unidade;
        this.endereco = endereco;
    }
}
