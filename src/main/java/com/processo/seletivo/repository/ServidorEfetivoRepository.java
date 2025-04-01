package com.processo.seletivo.repository;

import com.processo.seletivo.dtos.EnderecoFuncionalDTO;
import com.processo.seletivo.dtos.ServidorEfetivoDTO;
import com.processo.seletivo.dtos.ServidorEfetivoResumoDTO;
import com.processo.seletivo.models.ServidorEfetivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Integer> {
    @Query("""
    SELECT l.servidorEfetivo FROM Lotacao l
    WHERE l.unidade.unidId = :unidId
""")
    List<ServidorEfetivo> findByUnidadeId(@Param("unidId") Integer unidId);


    @Query("""
    SELECT new com.processo.seletivo.dtos.ServidorEfetivoResumoDTO(
        p.pesNome,
        YEAR(CURRENT_DATE) - YEAR(p.pesDataNascimento),
        u.unidNome,
        fp.fpBucket
    )
    FROM Lotacao l
    JOIN l.servidorEfetivo se
    JOIN se.pessoa p
    JOIN l.unidade u
    LEFT JOIN FotoPessoa fp ON fp.pessoa.pesId = p.pesId
    WHERE u.unidId = :unidId
""")
    Page<ServidorEfetivoResumoDTO> buscarResumoPorUnidade(@Param("unidId") Integer unidId, Pageable pageable);


    @Query("""
    SELECT se FROM ServidorEfetivo se
    JOIN se.pessoa p
    WHERE LOWER(p.pesNome) LIKE LOWER(CONCAT('%', :nome, '%'))
""")
    List<ServidorEfetivo> buscarPorNome(@Param("nome") String nome);


    @Query("""
    SELECT new com.processo.seletivo.dtos.EnderecoFuncionalDTO(
        p.pesNome,
        u.unidNome,
        CONCAT(e.endTipoLogradouro, ' ', e.endLogradouro, ', ', e.endNumero, ' - ',
               e.endBairro, ' - ', c.cidNome, '/', c.cidUf, ' - CEP: ', e.endCep)
    )
    FROM ServidorEfetivo se
    JOIN se.pessoa p
    JOIN Lotacao l ON l.servidorEfetivo = se
    JOIN Unidade u ON u = l.unidade
    JOIN UnidadeEndereco ue ON ue.unidade = u
    JOIN Endereco e ON e = ue.endereco
    JOIN Cidade c ON e.cidade = c
    WHERE LOWER(p.pesNome) LIKE LOWER(CONCAT('%', :nome, '%'))
""")
    Page<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNome(@Param("nome") String nome, Pageable pageable);


}
