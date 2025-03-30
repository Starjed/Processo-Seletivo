package com.processo.seletivo.services;

import com.processo.seletivo.models.Lotacao;
import com.processo.seletivo.repository.LotacaoRepository;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import com.processo.seletivo.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotacaoService {

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    public Page<Lotacao> listarTodos(Pageable pageable) {
        return lotacaoRepository.findAll(pageable);
    }

    public Lotacao salvar(Lotacao lotacao) {
        var servidorEfetivo = servidorEfetivoRepository.findById(lotacao.getServidorEfetivo().getPesId())
                .orElseThrow(() -> new RuntimeException("Servidor efetivo não encontrado"));
        var unidade = unidadeRepository.findById(lotacao.getUnidade().getUnidId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        lotacao.setServidorEfetivo(servidorEfetivo);
        lotacao.setUnidade(unidade);

        return lotacaoRepository.save(lotacao);
    }
    public Lotacao buscarPorId(Integer id) {
        return lotacaoRepository.findById(id).orElse(null);
    }

    public void deletar(Integer id) {
        lotacaoRepository.deleteById(id);
    }

}
