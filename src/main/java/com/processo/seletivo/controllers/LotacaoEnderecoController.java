package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.LotacaoEnderecoDTO;
import com.processo.seletivo.models.LotacaoEndereco;
import com.processo.seletivo.services.LotacaoEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotacoes-enderecos")
public class LotacaoEnderecoController {

    @Autowired
    private LotacaoEnderecoService service;

    @PostMapping
    public ResponseEntity<LotacaoEndereco> associar(@RequestBody LotacaoEnderecoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto.getLotacaoId(), dto.getEnderecoId()));
    }

    @GetMapping
    public ResponseEntity<Page<LotacaoEndereco>> listar(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotacaoEndereco> buscarPorId(@PathVariable Integer id) {
        LotacaoEndereco le = service.buscarPorId(id);
        return le != null ? ResponseEntity.ok(le) : ResponseEntity.notFound().build();
    }
}
