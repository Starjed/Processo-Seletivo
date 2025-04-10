package com.processo.seletivo.repository;

import com.processo.seletivo.models.FotoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Integer> {
    List<FotoPessoa> findByPessoaPesIdOrderByFpDataDesc(Integer pessoaId);
}