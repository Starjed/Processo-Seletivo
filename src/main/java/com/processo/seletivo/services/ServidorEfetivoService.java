package com.processo.seletivo.services;

import com.processo.seletivo.models.ServidorEfetivo;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServidorEfetivoService {

    @Autowired
    private ServidorEfetivoRepository repository;

    public List<ServidorEfetivo> listarTodos() {
        return repository.findAll();
    }

    public ServidorEfetivo salvar(ServidorEfetivo servidor) {
        return repository.save(servidor);
    }

    public ServidorEfetivo buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }


    public List<ServidorEfetivo> buscarPorUnidade(Long unidId) {
        return repository.findByUnidadeId(unidId);
    }

    public List<ServidorEfetivo> buscarPorNomeParcial(String nome) {
        return repository.buscarPorNome(nome);
    }
}
