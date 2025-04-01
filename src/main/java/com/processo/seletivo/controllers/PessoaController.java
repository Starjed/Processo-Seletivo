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

import java.util.Optional;


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

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPessoa(@PathVariable Integer id, @RequestBody Pessoa dto) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
        if (pessoaOpt.isEmpty()) return ResponseEntity.notFound().build();

        Pessoa pessoa = pessoaOpt.get();

        if (dto.getPesNome() != null) pessoa.setPesNome(dto.getPesNome());
        if (dto.getPesSexo() != null) pessoa.setPesSexo(dto.getPesSexo());
        if (dto.getPesDataNascimento() != null) pessoa.setPesDataNascimento(dto.getPesDataNascimento());
        if (dto.getPesMae() != null) pessoa.setPesMae(dto.getPesMae());
        if (dto.getPesPai() != null) pessoa.setPesPai(dto.getPesPai());

        return ResponseEntity.ok(pessoaRepository.save(pessoa));
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
