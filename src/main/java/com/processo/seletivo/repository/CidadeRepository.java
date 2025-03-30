// CidadeRepository.java
package com.processo.seletivo.repository;

import com.processo.seletivo.dtos.EnderecoFuncionalDTO;
import com.processo.seletivo.models.Cidade;
import com.processo.seletivo.models.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("""
        SELECT new com.processo.seletivo.dtos.EnderecoFuncionalDTO(
            p.pesNome,
            u.unidNome,
            CONCAT(e.endLogradouro, ', ', e.endBairro, ' - ', c.cidNome, ' - ', c.cidUf, ' - ', e.endCep)
        )
        FROM ServidorEfetivo se
        JOIN se.pessoa p
        JOIN Lotacao l ON l.servidorEfetivo = se
        JOIN Unidade u ON u = l.unidade
        JOIN PessoaEndereco pe ON pe.pessoa = p
        JOIN Endereco e ON e = pe.endereco
        JOIN e.cidade c
        WHERE LOWER(p.pesNome) LIKE LOWER(CONCAT('%', :nome, '%'))
    """)
    List<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNome(@Param("nome") String nome);

}
