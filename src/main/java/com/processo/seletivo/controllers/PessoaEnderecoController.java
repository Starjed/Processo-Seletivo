package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.PessoaEnderecoDTO;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.models.Endereco;
import com.processo.seletivo.models.PessoaEndereco;
import com.processo.seletivo.services.PessoaEnderecoService;
import com.processo.seletivo.services.PessoaService;
import com.processo.seletivo.services.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa-endereco")
public class PessoaEnderecoController {

    @Autowired
    private PessoaEnderecoService service;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> associarPessoaEndereco(@RequestBody PessoaEnderecoDTO request) {
        Pessoa pessoa = pessoaService.buscarPorId(request.getPessoaId());
        Endereco endereco = enderecoService.buscarPorId(request.getEnderecoId());

        if (pessoa == null || endereco == null) {
            return ResponseEntity.badRequest().body("Pessoa ou Endereço não encontrados.");
        }

        PessoaEndereco pe = new PessoaEndereco();
        pe.setPessoa(pessoa);
        pe.setEndereco(endereco);

        return ResponseEntity.ok(service.salvar(pe));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaEndereco>> listarTodos(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEndereco> buscarPorId(@PathVariable Integer id) {
        PessoaEndereco pe = service.buscarPorId(id);
        return pe != null ? ResponseEntity.ok(pe) : ResponseEntity.notFound().build();
    }
}
