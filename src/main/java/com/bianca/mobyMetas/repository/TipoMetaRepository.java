package com.bianca.mobyMetas.repository;

import com.bianca.mobyMetas.model.TipoMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMetaRepository extends JpaRepository<TipoMeta, Long> {
}
