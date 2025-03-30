package com.processo.seletivo.services;

import com.processo.seletivo.models.PessoaEndereco;
import com.processo.seletivo.repository.PessoaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaEnderecoService {

    @Autowired
    private PessoaEnderecoRepository repository;

    public PessoaEndereco salvar(PessoaEndereco pe) {
        return repository.save(pe);
    }

    public Page<PessoaEndereco> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PessoaEndereco buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
