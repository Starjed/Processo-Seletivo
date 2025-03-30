// UnidadeEnderecoRepository.java
package com.processo.seletivo.repository;

import com.processo.seletivo.models.UnidadeEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, Integer> {

}
