package com.processo.seletivo.services;

import com.processo.seletivo.models.Unidade;
import com.processo.seletivo.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    public List<Unidade> listarTodos() {
        return repository.findAll();
    }

    public Unidade salvar(Unidade unidade) {
        return repository.save(unidade);
    }
}
