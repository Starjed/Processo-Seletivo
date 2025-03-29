package com.processo.seletivo.repository;

import com.processo.seletivo.models.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {}