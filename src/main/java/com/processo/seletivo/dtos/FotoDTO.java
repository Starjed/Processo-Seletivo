package com.processo.seletivo.dtos;

public class FotoDTO {
    private String nome;
    private String url;

    public FotoDTO(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }
}