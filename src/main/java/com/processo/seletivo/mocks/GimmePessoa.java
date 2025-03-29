package com.processo.seletivo.mocks;

import com.processo.seletivo.models.*;
import com.processo.seletivo.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GimmePessoa implements CommandLineRunner {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    @PersistenceContext
    private EntityManager entityManager;

        @Override
        @Transactional
        public void run(String... args) throws Exception {

            Pessoa pessoa = new Pessoa();
            pessoa.setPesNome("Joao Teste");
            pessoa.setPesDataNascimento(LocalDate.of(1990, 4, 15));
            pessoa.setPesSexo("M");
            pessoa.setPesMae("Maria Teste");
            pessoa.setPesPai("José Teste");
            pessoa = pessoaRepository.save(pessoa);

            pessoa = entityManager.merge(pessoa);

            pessoa = pessoaRepository.findById(pessoa.getPesId()).orElseThrow();

            ServidorEfetivo servidor = new ServidorEfetivo();
            servidor.setSeMatricula("123456");
            servidor.setPessoa(pessoa);
            servidorEfetivoRepository.save(servidor);

            Unidade unidade = new Unidade();
            unidade.setUnidNome("Unidade Central");
            unidade = unidadeRepository.saveAndFlush(unidade);

            Lotacao lotacao = new Lotacao();
            lotacao.setServidorEfetivo(servidor);
            lotacao.setUnidade(unidade);
            lotacao.setLotDataLotacao(LocalDate.of(2020, 1, 1));
            lotacao.setLotDataRemocao(null);
            lotacaoRepository.save(lotacao);

            FotoPessoa foto = new FotoPessoa();
            foto.setPessoa(pessoa);
            foto.setFpData(LocalDate.now());
            foto.setFpBucket("https://example.com/foto_joao.jpg");
            foto.setFpHash("hash123");
            fotoPessoaRepository.save(foto);

            Endereco endereco = new Endereco();
            endereco.setEndLogradouro("Rua Principal");
            endereco.setEndBairro("Centro");
            endereco.setEndCidade("Cidade");
            endereco.setEndUf("UF");
            endereco.setEndCep("12345-678");
            endereco = enderecoRepository.save(endereco);

            PessoaEndereco pessoaEndereco = new PessoaEndereco();
            pessoaEndereco.setPessoa(pessoa); // a mesma do servidor
            pessoaEndereco.setEndereco(endereco);
            pessoaEnderecoRepository.save(pessoaEndereco);

            System.out.println("Mock criado");
        }
    }

