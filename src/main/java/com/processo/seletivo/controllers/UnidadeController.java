package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.UnidadeDTO;
import com.processo.seletivo.models.Unidade;
import com.processo.seletivo.repository.UnidadeRepository;
import com.processo.seletivo.services.EnderecoService;
import com.processo.seletivo.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    private final UnidadeRepository unidadeRepository;

    @Autowired
    private EnderecoService enderecoService;

    public UnidadeController(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

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

    @PutMapping("/editar/unidade/{id}")
    public ResponseEntity<?> editarUnidade(@PathVariable Integer id, @RequestBody UnidadeDTO dto) {
        Optional<Unidade> opt = unidadeRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Unidade unidade = opt.get();
        unidade.setUnidNome(dto.getUnidNome());
        unidade.setUnidSigla(dto.getUnidSigla());

        unidadeRepository.save(unidade);
        return ResponseEntity.ok("Unidade atualizada com sucesso.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        unidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
