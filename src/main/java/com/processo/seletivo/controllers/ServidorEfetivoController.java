package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.EnderecoFuncionalDTO;
import com.processo.seletivo.dtos.ServidorEfetivoDTO;
import com.processo.seletivo.models.ServidorEfetivo;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores-efetivos")
public class ServidorEfetivoController {

    @Autowired
    private ServidorEfetivoRepository repository;

    @GetMapping("/resumo/unidade/{unidId}")
    public List<ServidorEfetivoDTO> buscarResumoPorUnidade(@PathVariable Long unidId) {
        return repository.buscarResumoPorUnidade(unidId);
    }

    @GetMapping("/endereco-funcional")
    public List<EnderecoFuncionalDTO> buscarEnderecoFuncional(@RequestParam String nome) {
        return repository.buscarEnderecoFuncionalPorNome(nome);
    }
}
