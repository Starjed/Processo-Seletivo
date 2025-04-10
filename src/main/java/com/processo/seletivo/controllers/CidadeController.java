package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.CidadeDTO;
import com.processo.seletivo.models.Cidade;
import com.processo.seletivo.repository.CidadeRepository;
import com.processo.seletivo.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    private final CidadeRepository cidadeRepository;

    public CidadeController(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public Page<Cidade> listar(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cidadeService.listarTodos(pageable);
    }

    @PostMapping
    public ResponseEntity<Cidade> criar(@RequestBody Cidade cidade) {
        return ResponseEntity.ok(cidadeService.salvar(cidade));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCidade(@PathVariable Integer id, @RequestBody CidadeDTO dto) {
        Optional<Cidade> opt = cidadeRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Cidade cidade = opt.get();
        cidade.setCidNome(dto.getCidNome());
        cidade.setCidUf(dto.getCidUf());
        cidadeRepository.save(cidade);

        return ResponseEntity.ok("Cidade atualizada.");
    }

@GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Integer id) {
        return cidadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
