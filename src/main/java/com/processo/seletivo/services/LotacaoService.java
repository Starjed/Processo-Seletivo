package com.processo.seletivo.services;

import com.processo.seletivo.models.Lotacao;
import com.processo.seletivo.repository.LotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotacaoService {

    @Autowired
    private LotacaoRepository repository;

    public List<Lotacao> listarTodos() {
        return repository.findAll();
    }

    public Lotacao salvar(Lotacao lotacao) {
        return repository.save(lotacao);
    }
}