package com.processo.seletivo.dtos;

public class ServidorEfetivoDTO {
    private String nome;
    private Integer idade;

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String unidade;
    private String fotoUrl;

    public ServidorEfetivoDTO(String nome, Integer idade, String unidade, String fotoUrl) {
        this.nome = nome;
        this.idade = idade;
        this.unidade = unidade;
        this.fotoUrl = (fotoUrl != null) ? fotoUrl : "https://example.com/default.jpg";
    }
}
