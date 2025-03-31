package com.processo.seletivo.controllers;

import com.processo.seletivo.models.Lotacao;
import com.processo.seletivo.models.ServidorTemporario;
import com.processo.seletivo.services.LotacaoService;
import com.processo.seletivo.services.ServidorEfetivoService;
import com.processo.seletivo.services.ServidorTemporarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servidores")
public class ServidorTemporarioController {

    @Autowired
    private ServidorTemporarioService servidorTemporarioService;

    @GetMapping("/temporarios")
    public Page<ServidorTemporario> listarTemporarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "pesNome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return servidorTemporarioService.listarTodos(pageable);
    }

    @PostMapping("/temporarios/criar")
    public ResponseEntity<ServidorTemporario> criarTemporario(@RequestBody ServidorTemporario servidor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servidorTemporarioService.salvar(servidor));
    }

    @PutMapping("/temporarios/atualizar/{id}")
    public ResponseEntity<ServidorTemporario> atualizarTemporario(@PathVariable Integer id, @RequestBody ServidorTemporario servidor) {
        servidor.setPesId(id);
        return ResponseEntity.ok(servidorTemporarioService.salvar(servidor));
    }

    @PutMapping("/temporarios/excluir/{id}")
    public ResponseEntity<ServidorTemporario> deletar(@PathVariable Integer id) {
        servidorTemporarioService.deletar((id));
        return ResponseEntity.noContent().build();
    }
}
