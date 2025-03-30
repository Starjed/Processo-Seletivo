package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.UnidadeEnderecoDTO;
import com.processo.seletivo.models.UnidadeEndereco;
import com.processo.seletivo.services.UnidadeEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unidade-endereco")
public class UnidadeEnderecoController {

    @Autowired
    private UnidadeEnderecoService service;

    @PostMapping("/associar")
    public ResponseEntity<?> associar(@RequestBody UnidadeEnderecoDTO dto) {
        UnidadeEndereco associado = service.associar(dto.getUnidadeId(), dto.getEnderecoId());
        return ResponseEntity.ok("Unidade " + associado.getUnidade().getUnidId()
                + " associada ao endereço " + associado.getEndereco().getEndId());
    }
}
