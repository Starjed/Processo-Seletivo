package com.processo.seletivo.services;

import com.processo.seletivo.models.ServidorTemporario;
import com.processo.seletivo.repository.ServidorTemporarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServidorTemporarioService {

    @Autowired
    private ServidorTemporarioRepository repository;

    public Page<ServidorTemporario> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ServidorTemporario salvar(ServidorTemporario servidor) {
        return repository.save(servidor);
    }

    public ServidorTemporario buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}