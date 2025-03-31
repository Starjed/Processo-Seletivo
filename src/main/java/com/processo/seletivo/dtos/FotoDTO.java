package com.processo.seletivo.dtos;

import lombok.Getter;

@Getter
public class FotoDTO {
    private String nome;
    private String url;

    public FotoDTO(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

}