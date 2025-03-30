package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.EnderecoFuncionalDTO;
import com.processo.seletivo.dtos.ServidorEfetivoDTO;
import com.processo.seletivo.models.ServidorEfetivo;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import com.processo.seletivo.services.ServidorEfetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores-efetivos")
public class ServidorEfetivoController {

    @Autowired
    private ServidorEfetivoService servidorEfetivoService;

    @GetMapping("/efetivos")
    public Page<ServidorEfetivo> listarEfetivos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "pesNome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return servidorEfetivoService.listarTodos(pageable);
    }

    @PostMapping("/efetivos")
    public ResponseEntity<ServidorEfetivo> criarEfetivo(@RequestBody ServidorEfetivo servidor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servidorEfetivoService.salvar(servidor));
    }


    @PutMapping("/efetivos/{id}")
    public ResponseEntity<ServidorEfetivo> atualizarEfetivo(@PathVariable Integer id, @RequestBody ServidorEfetivo servidor) {
        servidor.setPesId(id);
        return ResponseEntity.ok(servidorEfetivoService.salvar(servidor));
    }

    @GetMapping("/efetivos/{id}")
    public ResponseEntity<ServidorEfetivo> buscarPorId(@PathVariable Integer id) {
        ServidorEfetivo se = servidorEfetivoService.buscarPorId(id);
        return se != null ? ResponseEntity.ok(se) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/efetivos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        servidorEfetivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-unidade/{unidId}")
    public ResponseEntity<Page<ServidorEfetivoDTO>> listarPorUnidade(Pageable pageable, @PathVariable Integer unidId) {
        return ResponseEntity.ok(servidorEfetivoService.buscarResumoPorUnidade(pageable, unidId));
    }

    @GetMapping("/enderecos-funcionais")
    public ResponseEntity<Page<EnderecoFuncionalDTO>> buscarEnderecoFuncionalPorNome(
            @RequestParam("nome") String nome, Pageable pageable) {
        Page<EnderecoFuncionalDTO> resultado = servidorEfetivoService.buscarEnderecoFuncionalPorNome(nome, pageable);
        return ResponseEntity.ok(resultado);
    }

}
