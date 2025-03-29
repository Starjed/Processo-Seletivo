package com.processo.seletivo.repository;

import com.processo.seletivo.models.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> {
}
