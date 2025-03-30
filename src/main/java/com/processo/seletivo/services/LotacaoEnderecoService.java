package com.processo.seletivo.services;

import com.processo.seletivo.models.Endereco;
import com.processo.seletivo.models.Lotacao;
import com.processo.seletivo.models.LotacaoEndereco;
import com.processo.seletivo.repository.EnderecoRepository;
import com.processo.seletivo.repository.LotacaoEnderecoRepository;
import com.processo.seletivo.repository.LotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LotacaoEnderecoService {

    @Autowired
    private LotacaoEnderecoRepository repository;

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<LotacaoEndereco> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public LotacaoEndereco salvar(Integer lotacaoId, Integer enderecoId) {
        Lotacao lotacao = lotacaoRepository.findById(lotacaoId)
                .orElseThrow(() -> new RuntimeException("Lotacao nao encontrada"));
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereco nao encontrado"));

        LotacaoEndereco le = new LotacaoEndereco();
        le.setLotacao(lotacao);
        le.setEndereco(endereco);
        return repository.save(le);
    }

    public LotacaoEndereco buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}