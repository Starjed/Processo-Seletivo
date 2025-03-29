package com.processo.seletivo.controllers;

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

    @GetMapping
    public List<ServidorEfetivo> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ServidorEfetivo salvar(@RequestBody ServidorEfetivo servidor) {
        return repository.save(servidor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivo> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivo> atualizar(@PathVariable Long id, @RequestBody ServidorEfetivo atualizado) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        atualizado.setPesId(id);
        return ResponseEntity.ok(repository.save(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumo/unidade/{unidId}")
    public List<ServidorEfetivoDTO> buscarResumoPorUnidade(@PathVariable Long unidId) {
        return repository.buscarResumoPorUnidade(unidId);
    }
}
