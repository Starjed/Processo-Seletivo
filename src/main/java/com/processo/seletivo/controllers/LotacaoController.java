package com.processo.seletivo.controllers;

import com.processo.seletivo.dtos.LotacaoDTO;
import com.processo.seletivo.models.Lotacao;
import com.processo.seletivo.models.ServidorEfetivo;
import com.processo.seletivo.models.Unidade;
import com.processo.seletivo.repository.LotacaoRepository;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import com.processo.seletivo.repository.UnidadeRepository;
import com.processo.seletivo.services.LotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/lotacoes")
public class LotacaoController {

    @Autowired
    private LotacaoService lotacaoService;

    private final LotacaoRepository lotacaoRepository;

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final UnidadeRepository unidadeRepository;

    public LotacaoController(LotacaoRepository lotacaoRepository, ServidorEfetivoRepository servidorEfetivoRepository, UnidadeRepository unidadeRepository) {
        this.lotacaoRepository = lotacaoRepository;
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @PostMapping
    public ResponseEntity<Lotacao> criar(@RequestBody Lotacao lotacao) {
        return ResponseEntity.ok(lotacaoService.salvar(lotacao));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody LotacaoDTO dto) {
        Optional<Lotacao> opt = lotacaoRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Lotacao lotacao = opt.get();

        ServidorEfetivo servidor = servidorEfetivoRepository.findById(dto.getServidorId())
                .orElseThrow(() -> new RuntimeException("Servidor Efetivo não encontrado"));
        Unidade unidade = unidadeRepository.findById(dto.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        lotacao.setServidorEfetivo(servidor);
        lotacao.setUnidade(unidade);
        lotacao.setLotDataLotacao(dto.getLotDataLotacao());
        lotacao.setLotDataRemocao(dto.getLotDataRemocao());
        lotacao.setLotPortaria(dto.getLotPortaria());

        lotacaoRepository.save(lotacao);
        return ResponseEntity.ok("Lotação atualizada com sucesso.");
    }

    @GetMapping
    public ResponseEntity<Page<Lotacao>> listar(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(lotacaoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lotacao> buscarPorId(@PathVariable Integer id) {
        Lotacao lotacao = lotacaoService.buscarPorId(id);
        return lotacao != null ? ResponseEntity.ok(lotacao) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        lotacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
