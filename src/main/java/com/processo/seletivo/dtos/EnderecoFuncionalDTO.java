package com.processo.seletivo.dtos;

public class EnderecoFuncionalDTO {
    private String servidor;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    private String unidade;
    private String endereco;

    public EnderecoFuncionalDTO(String servidor, String unidade, String endereco) {
        this.servidor = servidor;
        this.unidade = unidade;
        this.endereco = endereco;
    }
}
