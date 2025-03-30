package com.processo.seletivo.services;

import com.processo.seletivo.models.Cidade;
import com.processo.seletivo.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Page<Cidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Cidade salvar(Cidade cidade) {
        return repository.save(cidade);
    }

    public Optional<Cidade> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

}
