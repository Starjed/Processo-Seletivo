// UnidadeController.java
package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.UnidadeEnderecoDTO;
import com.processo.seletivo.models.Endereco;
import com.processo.seletivo.models.Unidade;
import com.processo.seletivo.services.EnderecoService;
import com.processo.seletivo.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public Page<Unidade> listarTodos(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return unidadeService.listarTodos(pageable);
    }

    @PostMapping
    public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade) {
        return ResponseEntity.ok(unidadeService.salvar(unidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> atualizar(@PathVariable Integer id, @RequestBody Unidade unidade) {
        unidade.setUnidId(id);
        return ResponseEntity.ok(unidadeService.salvar(unidade));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        unidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
