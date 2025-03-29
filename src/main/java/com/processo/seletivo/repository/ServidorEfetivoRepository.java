package com.processo.seletivo.repository;

import com.processo.seletivo.dtos.ServidorEfetivoDTO;
import com.processo.seletivo.models.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
    @Query("""
    SELECT l.servidorEfetivo FROM Lotacao l
    WHERE l.unidade.unidId = :unidId
""")
    List<ServidorEfetivo> findByUnidadeId(@Param("unidId") Long unidId);


    @Query("""
    SELECT new com.processo.seletivo.dtos.ServidorEfetivoDTO(
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
    List<ServidorEfetivoDTO> buscarResumoPorUnidade(@Param("unidId") Long unidId);


    @Query("""
    SELECT se FROM ServidorEfetivo se
    JOIN se.pessoa p
    WHERE LOWER(p.pesNome) LIKE LOWER(CONCAT('%', :nome, '%'))
""")
    List<ServidorEfetivo> buscarPorNome(@Param("nome") String nome);
}
