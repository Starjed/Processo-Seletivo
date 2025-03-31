package com.processo.seletivo.controllers;

import com.processo.seletivo.models.Lotacao;
import com.processo.seletivo.services.LotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/lotacoes")
public class LotacaoController {

    @Autowired
    private LotacaoService lotacaoService;

    @PostMapping
    public ResponseEntity<Lotacao> criar(@RequestBody Lotacao lotacao) {
        return ResponseEntity.ok(lotacaoService.salvar(lotacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lotacao> atualizar(@PathVariable Integer id, @RequestBody Lotacao lotacao) {
        lotacao.setLotId(id);
        return ResponseEntity.ok(lotacaoService.salvar(lotacao));
    }

    @GetMapping
    public ResponseEntity<Page<Lotacao>> listar(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(lotacaoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lotacao> buscarPorId(@PathVariable Integer id) {
        Lotacao lotacao = lotacaoService.buscarPorId(id);
        return lotacao != null ? ResponseEntity.ok(lotacao) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        lotacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
