package com.processo.seletivo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServidorEfetivoResumoDTO {
    private String nome;
    private Integer idade;
    private String unidade;
    private String fotoUrl;


    private static final String DEFAULT_FOTO =
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=";


    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = (fotoUrl != null && !fotoUrl.isEmpty()) ? fotoUrl : DEFAULT_FOTO;
    }
}

