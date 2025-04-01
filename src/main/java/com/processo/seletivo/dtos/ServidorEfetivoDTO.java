package com.processo.seletivo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServidorEfetivoDTO {
    private String nome;
    private Integer idade;
    private Integer pesId;
    private String unidade;
    private String fotoUrl;
    private String seMatricula;

    public ServidorEfetivoDTO(String nome, Integer idade, String unidade, String fotoUrl) {
        this.nome = nome;
        this.idade = idade;
        this.unidade = unidade;
        this.fotoUrl = (fotoUrl != null) ? fotoUrl : "https://example.com/default.jpg";
    }
}
