package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.ServidorTemporarioDTO;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.models.ServidorTemporario;
import com.processo.seletivo.repository.PessoaRepository;
import com.processo.seletivo.repository.ServidorTemporarioRepository;
import com.processo.seletivo.services.ServidorTemporarioService;
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
public class ServidorTemporarioController {

    @Autowired
    private ServidorTemporarioService servidorTemporarioService;

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    private final PessoaRepository pessoaRepository;

    public ServidorTemporarioController(ServidorTemporarioRepository servidorTemporarioRepository, PessoaRepository pessoaRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/temporarios")
    public Page<ServidorTemporario> listarTemporarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "pesId") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return servidorTemporarioService.listarTodos(pageable);
    }


    @GetMapping("/temporarios/{id}")
    public ResponseEntity<ServidorTemporario> buscarPorId(@PathVariable Integer id) {
        ServidorTemporario st = servidorTemporarioService.buscarPorId(id);
        if (st == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(st);
    }

    @PostMapping("/temporarios/criar")
    public ResponseEntity<?> criarTemporario(@RequestBody ServidorTemporarioDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        ServidorTemporario st = new ServidorTemporario();
        st.setPessoa(pessoa);
        st.setStDataAdmissao(dto.getStDataAdmissao());
        st.setStDataDemissao(dto.getStDataDemissao());

        servidorTemporarioService.salvar(st);
        return ResponseEntity.status(HttpStatus.CREATED).body("Servidor temporário criado com sucesso.");
    }

    @PutMapping("/temporarios/editar/{id}")
    public ResponseEntity<?> atualizarTemporario(@PathVariable Integer id, @RequestBody ServidorTemporarioDTO dto) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(dto.getPessoaId());
        if (pessoaOpt.isEmpty()) return ResponseEntity.badRequest().body("Pessoa não encontrada.");

        Optional<ServidorTemporario> servidorOpt = servidorTemporarioRepository.findById(id);
        if (servidorOpt.isEmpty()) return ResponseEntity.notFound().build();

        ServidorTemporario servidor = servidorOpt.get();
        servidor.setPessoa(pessoaOpt.get());
        servidor.setStDataAdmissao(dto.getStDataAdmissao());
        servidor.setStDataDemissao(dto.getStDataDemissao());

        servidorTemporarioRepository.save(servidor);
        return ResponseEntity.ok("Servidor Temporário atualizado com sucesso.");
    }

    @PutMapping("/temporarios/excluir/{id}")
    public ResponseEntity<ServidorTemporario> deletar(@PathVariable Integer id) {
        servidorTemporarioService.deletar((id));
        return ResponseEntity.noContent().build();
    }
}
