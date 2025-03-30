package com.processo.seletivo.services;

import com.processo.seletivo.models.Endereco;
import com.processo.seletivo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private CidadeService cidadeService; // ← novo

    public Page<Endereco> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Endereco salvar(Endereco endereco) {
        if (endereco.getCidade() != null && endereco.getCidade().getCidId() != null) {
            endereco.setCidade(cidadeService.buscarPorId(endereco.getCidade().getCidId()).orElse(null));
        }
        return repository.save(endereco);
    }

    public Endereco buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

}
