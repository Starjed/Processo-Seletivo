package com.processo.seletivo.services;

import com.processo.seletivo.models.ServidorTemporario;
import com.processo.seletivo.repository.ServidorTemporarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServidorTemporarioService {

    @Autowired
    private ServidorTemporarioRepository repository;

    public List<ServidorTemporario> listarTodos() {
        return repository.findAll();
    }

    public ServidorTemporario salvar(ServidorTemporario servidor) {
        return repository.save(servidor);
    }
}
