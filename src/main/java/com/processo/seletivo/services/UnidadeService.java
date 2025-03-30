package com.processo.seletivo.services;

import com.processo.seletivo.models.Unidade;
import com.processo.seletivo.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    public Page<Unidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Unidade salvar(Unidade unidade) {
        return repository.save(unidade);
    }

    public Unidade buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
