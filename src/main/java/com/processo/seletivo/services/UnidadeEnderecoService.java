package com.processo.seletivo.services;

import com.processo.seletivo.models.Endereco;
import com.processo.seletivo.models.Unidade;
import com.processo.seletivo.models.UnidadeEndereco;
import com.processo.seletivo.repository.EnderecoRepository;
import com.processo.seletivo.repository.UnidadeEnderecoRepository;
import com.processo.seletivo.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadeEnderecoService {

    @Autowired
    private UnidadeEnderecoRepository unidadeEnderecoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public UnidadeEndereco associar(Integer unidId, Integer endId) {
        Unidade unidade = unidadeRepository.findById(unidId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        Endereco endereco = enderecoRepository.findById(endId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        UnidadeEndereco unidadeEndereco = new UnidadeEndereco();
        unidadeEndereco.setUnidade(unidade);
        unidadeEndereco.setEndereco(endereco);

        return unidadeEnderecoRepository.save(unidadeEndereco);
    }
}
