package com.processo.seletivo.services;

import com.processo.seletivo.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.processo.seletivo.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa buscarPorId(Integer id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Page<Pessoa> listarTodosPaginado(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public void deletar(Integer id) {
        pessoaRepository.deleteById(id);
    }

}

