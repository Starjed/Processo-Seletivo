package com.processo.seletivo.repository;

import com.processo.seletivo.models.LotacaoEndereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoEnderecoRepository extends JpaRepository<LotacaoEndereco, Integer> {
    Page<LotacaoEndereco> findAll(Pageable pageable);
}
