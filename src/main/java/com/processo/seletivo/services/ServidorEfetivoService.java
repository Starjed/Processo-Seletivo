package com.processo.seletivo.services;

import com.processo.seletivo.dtos.EnderecoFuncionalDTO;
import com.processo.seletivo.dtos.ServidorEfetivoDTO;
import com.processo.seletivo.models.Pessoa;
import com.processo.seletivo.models.ServidorEfetivo;
import com.processo.seletivo.repository.ServidorEfetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ServidorEfetivoService {

    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Autowired
    private PessoaService pessoaService;

    public Page<ServidorEfetivo> listarTodos(Pageable pageable) {
        return servidorEfetivoRepository.findAll(pageable);
    }

    public ServidorEfetivo salvar(ServidorEfetivo servidor) {
        if (servidor.getPessoa() != null && servidor.getPessoa().getPesId() != null) {
            Pessoa pessoaPersistida = pessoaService.buscarPorId(servidor.getPessoa().getPesId());
            servidor.setPessoa(pessoaPersistida);
        }

        return servidorEfetivoRepository.save(servidor);
    }

    public ServidorEfetivo buscarPorId(Integer id) {
        return servidorEfetivoRepository.findById(id).orElse(null);
    }

    public void deletar(Integer id) {
        servidorEfetivoRepository.deleteById(id);
    }

    public Page<ServidorEfetivoDTO> buscarResumoPorUnidade(Pageable pageable, Integer unidId) {
        return servidorEfetivoRepository.buscarResumoPorUnidade(unidId, pageable);
    }

    public Page<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNome(String nome, Pageable pageable) {
        return servidorEfetivoRepository.buscarEnderecoFuncionalPorNome(nome, pageable);
    }

}
