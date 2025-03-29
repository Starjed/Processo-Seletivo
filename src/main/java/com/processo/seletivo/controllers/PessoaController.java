package com.processo.seletivo.controllers;

import jakarta.annotation.PostConstruct;
import com.processo.seletivo.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.processo.seletivo.services.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @PostConstruct
    public void init() {
        System.out.println(">>> PessoaController carregado!");
    }

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodos() {
        return pessoaService.listarTodos();
    }

    @GetMapping("/fake")
    public List<String> listarFake() {
        System.out.println("caiu aqui");
        return List.of("João da Silva", "Maria Oliveira", "Carlos Souza");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        pessoa.setPesId(id);
        Pessoa atualizada = pessoaService.salvar(pessoa);
        return ResponseEntity.ok(atualizada);
    }
}
