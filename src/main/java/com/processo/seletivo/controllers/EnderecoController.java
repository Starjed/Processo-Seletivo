package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.EnderecoDTO;
import com.processo.seletivo.models.Cidade;
import com.processo.seletivo.models.Endereco;
import com.processo.seletivo.repository.CidadeRepository;
import com.processo.seletivo.repository.EnderecoRepository;
import com.processo.seletivo.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    private final EnderecoRepository enderecoRepository;

    private final CidadeRepository cidadeRepository;

    public EnderecoController(EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public Page<Endereco> listarTodos(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.listarTodos(pageable);
    }

    @PostMapping
    public ResponseEntity<Endereco> criar(@RequestBody Endereco endereco) {
        return ResponseEntity.ok(service.salvar(endereco));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEndereco(@PathVariable Integer id, @RequestBody EnderecoDTO dto) {
        Optional<Endereco> opt = enderecoRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Endereco endereco = opt.get();
        Cidade cidade = cidadeRepository.findById(dto.getCidadeId()).orElseThrow();

        endereco.setEndTipoLogradouro(dto.getEndTipoLogradouro());
        endereco.setEndLogradouro(dto.getEndLogradouro());
        endereco.setEndNumero(dto.getEndNumero());
        endereco.setEndBairro(dto.getEndBairro());
        endereco.setEndCep(dto.getEndCep());
        endereco.setEndUf(dto.getEndUf());
        endereco.setCidade(cidade);
        enderecoRepository.save(endereco);

        return ResponseEntity.ok("Endereço atualizado.");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
