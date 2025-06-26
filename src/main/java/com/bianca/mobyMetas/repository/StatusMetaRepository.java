package com.bianca.mobyMetas.repository;

import com.bianca.mobyMetas.model.StatusMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusMetaRepository extends JpaRepository<StatusMeta, Long> {
    Optional<StatusMeta> findByNomeIgnoreCase(String nome);
}