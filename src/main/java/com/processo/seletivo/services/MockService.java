package com.processo.seletivo.services;

import com.processo.seletivo.models.*;
import com.processo.seletivo.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MockService {

    @Autowired private PessoaRepository pessoaRepository;
    @Autowired private ServidorEfetivoRepository servidorEfetivoRepository;
    @Autowired private UnidadeRepository unidadeRepository;
    @Autowired private LotacaoRepository lotacaoRepository;
    @Autowired private FotoPessoaRepository fotoPessoaRepository;
    @Autowired private EnderecoRepository enderecoRepository;
    @Autowired private PessoaEnderecoRepository pessoaEnderecoRepository;
    @Autowired private CidadeRepository cidadeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void gerarMock() {

        List<String> nomes = List.of("Ana Clara", "Bruno Silva", "Carla Souza", "Daniel Rocha", "Eduarda Lima");
        List<String> logradouros = List.of("Rua das Flores", "Avenida Brasil", "Travessa da Paz", "Rua das Acácias", "Alameda Central");

        Cidade cidade = new Cidade();
        cidade.setCidNome("Cidade Exemplo");
        cidade.setCidUf("SP");
        cidade = cidadeRepository.save(cidade);

        List<Unidade> unidades = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Unidade unidade = new Unidade();
            unidade.setUnidNome("Unidade " + i);
            unidade.setUnidSigla("Uni" + i);
            unidade = unidadeRepository.save(unidade);
            unidades.add(unidade);
        }

        List<Endereco> enderecos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Endereco endereco = new Endereco();
            endereco.setEndLogradouro(logradouros.get(i));
            endereco.setEndBairro("Bairro " + (i + 1));
            endereco.setEndUf("UF");
            endereco.setEndCep("1234" + i + "-000");
            endereco.setCidade(cidade);
            endereco.setEndNumero(100);
            endereco.setEndTipoLogradouro("Casa");
            endereco = enderecoRepository.save(endereco);
            enderecos.add(endereco);
        }

        for (int i = 0; i < 5; i++) {
            Pessoa pessoa = new Pessoa();
            pessoa.setPesNome(nomes.get(i));
            pessoa.setPesDataNascimento(LocalDate.of(1990, 1, i + 1));
            pessoa.setPesSexo("M");
            pessoa.setPesMae("Mãe " + nomes.get(i));
            pessoa.setPesPai("Pai " + nomes.get(i));
            pessoa = pessoaRepository.save(pessoa);

            pessoa = entityManager.merge(pessoa); // opcional, mas mantém consistência

            ServidorEfetivo servidor = new ServidorEfetivo();
            servidor.setSeMatricula("MAT" + (i + 1));
            servidor.setPessoa(pessoa);
            servidorEfetivoRepository.save(servidor);

            Lotacao lotacao = new Lotacao();
            lotacao.setServidorEfetivo(servidor);
            lotacao.setUnidade(unidades.get(i % unidades.size()));
            lotacao.setLotDataLotacao(LocalDate.of(2020, 1, 1));
            lotacao.setLotDataRemocao(null);
            lotacaoRepository.save(lotacao);

            FotoPessoa foto = new FotoPessoa();
            foto.setPessoa(pessoa);
            foto.setFpData(LocalDate.now());
            foto.setFpBucket("fotos");
            foto.setFpHash(UUID.randomUUID().toString());
            fotoPessoaRepository.save(foto);

            PessoaEndereco pessoaEndereco = new PessoaEndereco();
            pessoaEndereco.setPessoa(pessoa);
            pessoaEndereco.setEndereco(enderecos.get(i));
            pessoaEnderecoRepository.save(pessoaEndereco);
        }
    }
}
