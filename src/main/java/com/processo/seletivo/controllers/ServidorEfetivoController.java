package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.EnderecoFuncionalDTO;
import com.processo.seletivo.dtos.ServidorEfetivoDTO;
import com.processo.seletivo.dtos.ServidorEfetivoResumoDTO;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.models.ServidorEfetivo;
import com.processo.seletivo.repository.PessoaRepository;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import com.processo.seletivo.services.ServidorEfetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/servidores")
public class ServidorEfetivoController {

    @Autowired
    private ServidorEfetivoService servidorEfetivoService;

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final PessoaRepository pessoaRepository;

    public ServidorEfetivoController(ServidorEfetivoRepository servidorEfetivoRepository, PessoaRepository pessoaRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/efetivos")
    public Page<ServidorEfetivo> listarEfetivos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "pesNome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return servidorEfetivoService.listarTodos(pageable);
    }

    @PostMapping("/efetivos")
    public ResponseEntity<?> criarEfetivo(@RequestBody ServidorEfetivo servidor) {
        if (servidor.getPessoa() == null || servidor.getPessoa().getPesId() == null) {
            return ResponseEntity.badRequest().body("Pessoa deve ser informada com ID.");
        }

        Pessoa pessoa = pessoaRepository.findById(servidor.getPessoa().getPesId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        servidor.setPessoa(pessoa);

        ServidorEfetivo salvo = servidorEfetivoService.salvar(servidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/efetivos/editar/{id}")
    public ResponseEntity<?> editarEfetivo(@PathVariable Integer id, @RequestBody ServidorEfetivoDTO dto) {
        Optional<ServidorEfetivo> opt = servidorEfetivoRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        ServidorEfetivo servidor = opt.get();
        servidor.setSeMatricula(dto.getSeMatricula());
        servidorEfetivoRepository.save(servidor);

        return ResponseEntity.ok("Servidor efetivo atualizado.");
    }

    @GetMapping("/efetivos/{id}")
    public ResponseEntity<ServidorEfetivo> buscarPorId(@PathVariable Integer id) {
        ServidorEfetivo se = servidorEfetivoService.buscarPorId(id);
        return se != null ? ResponseEntity.ok(se) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/efetivos/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        servidorEfetivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-unidade/{unidId}")
    public ResponseEntity<Page<ServidorEfetivoResumoDTO>> listarPorUnidade(Pageable pageable, @PathVariable Integer unidId) {
        return ResponseEntity.ok(servidorEfetivoService.buscarResumoPorUnidade(pageable, unidId));
    }

    @GetMapping("/enderecos-funcionais")
    public ResponseEntity<Page<EnderecoFuncionalDTO>> buscarEnderecoFuncionalPorNome(
            @RequestParam("nome") String nome, Pageable pageable) {
        Page<EnderecoFuncionalDTO> resultado = servidorEfetivoService.buscarEnderecoFuncionalPorNome(nome, pageable);
        return ResponseEntity.ok(resultado);
    }

}
