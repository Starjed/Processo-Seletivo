package com.processo.seletivo.repository;

import com.processo.seletivo.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByPesNome(String nome);

    List<Pessoa> findByPesNomeContainingIgnoreCase(String parteDoNome);

    List<Pessoa> findByPesMae(String nomeDaMae);

    List<Pessoa> findByPesSexo(String sexo);

    List<Pessoa> findByPesDataNascimentoAfter(LocalDate data);

    List<Pessoa> findByPesNomeAndPesSexo(String nome, String sexo);
}
