package com.processo.seletivo.controllers;

import com.processo.seletivo.models.*;
import com.processo.seletivo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servidores")
public class ServidorController {

//    @Autowired
//    private ServidorEfetivoService servidorEfetivoService;
//    @Autowired
//    private ServidorTemporarioService servidorTemporarioService;
//    @Autowired
//    private UnidadeService unidadeService;
//    @Autowired
//    private LotacaoService lotacaoService;
//
//    @GetMapping("/efetivos")
//    public List<ServidorEfetivo> listarEfetivos() {
//        return servidorEfetivoService.listarTodos();
//    }
//
//    @PostMapping("/efetivos")
//    public ResponseEntity<ServidorEfetivo> criarEfetivo(@RequestBody ServidorEfetivo servidor) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(servidorEfetivoService.salvar(servidor));
//    }
//
//    @PutMapping("/efetivos/{id}")
//    public ResponseEntity<ServidorEfetivo> atualizarEfetivo(@PathVariable Long id, @RequestBody ServidorEfetivo servidor) {
//        servidor.setId(id);
//        return ResponseEntity.ok(servidorEfetivoService.salvar(servidor));
//    }
//
//    @GetMapping("/efetivos/unidade/{unidId}")
//    public List<Map<String, Object>> listarPorUnidade(@PathVariable Long unidId) {
//        return servidorEfetivoService.buscarPorUnidade(unidId)
//                .stream()
//                .map(s -> Map.of(
//                        "nome", s.getNome(),
//                        "idade", s.getIdade(),
//                        "unidade", s.getUnidade().getNome(),
//                        "fotografia", s.getFotografia()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/efetivos/endereco")
//    public List<Map<String, Object>> buscarEnderecoPorNome(@RequestParam String nome) {
//        return servidorEfetivoService.buscarPorNomeParcial(nome)
//                .stream()
//                .map(s -> Map.of(
//                        "nome", s.getNome(),
//                        "unidade", s.getUnidade().getNome(),
//                        "endereco", s.getUnidade().getEndereco()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // CRUD Servidor Temporário
//    @GetMapping("/temporarios")
//    public List<ServidorTemporario> listarTemporarios() {
//        return servidorTemporarioService.listarTodos();
//    }
//
//    @PostMapping("/temporarios")
//    public ResponseEntity<ServidorTemporario> criarTemporario(@RequestBody ServidorTemporario servidor) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(servidorTemporarioService.salvar(servidor));
//    }
//
//    @PutMapping("/temporarios/{id}")
//    public ResponseEntity<ServidorTemporario> atualizarTemporario(@PathVariable Long id, @RequestBody ServidorTemporario servidor) {
//        servidor.setId(id);
//        return ResponseEntity.ok(servidorTemporarioService.salvar(servidor));
//    }
//
//    @GetMapping("/unidades")
//    public List<Unidade> listarUnidades() {
//        return unidadeService.listarTodos();
//    }
//
//    @PostMapping("/unidades")
//    public ResponseEntity<Unidade> criarUnidade(@RequestBody Unidade unidade) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(unidadeService.salvar(unidade));
//    }
//
//    @PutMapping("/unidades/{id}")
//    public ResponseEntity<Unidade> atualizarUnidade(@PathVariable Long id, @RequestBody Unidade unidade) {
//        unidade.setId(id);
//        return ResponseEntity.ok(unidadeService.salvar(unidade));
//    }
//
//    // CRUD Lotação
//    @GetMapping("/lotacoes")
//    public List<Lotacao> listarLotacoes() {
//        return lotacaoService.listarTodos();
//    }
//
//    @PostMapping("/lotacoes")
//    public ResponseEntity<Lotacao> criarLotacao(@RequestBody Lotacao lotacao) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(lotacaoService.salvar(lotacao));
//    }
//
//    @PutMapping("/lotacoes/{id}")
//    public ResponseEntity<Lotacao> atualizarLotacao(@PathVariable Long id, @RequestBody Lotacao lotacao) {
//        lotacao.setId(id);
//        return ResponseEntity.ok(lotacaoService.salvar(lotacao));
//    }
}
