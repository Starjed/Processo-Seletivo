package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.PessoaDTO;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.processo.seletivo.services.PessoaService;


@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "pesNome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(pessoaService.listarTodosPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Integer id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(pessoa));
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoa.setPesNome(dto.getPesNome());
        pessoa.setPesDataNascimento(dto.getPesDataNascimento());
        pessoa.setPesSexo(dto.getPesSexo());
        pessoa.setPesMae(dto.getPesMae());
        pessoa.setPesPai(dto.getPesPai());

        pessoaRepository.save(pessoa);
        return ResponseEntity.ok("Pessoa atualizada com sucesso");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
