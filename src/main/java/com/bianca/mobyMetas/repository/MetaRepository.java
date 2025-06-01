package com.bianca.mobyMetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bianca.mobyMetas.model.Meta;

import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Long> {

    List<Meta> findByStatusNome(String status);

    List<Meta> findByUsuarioId(Long usuarioId);

    List<Meta> findByUsuario_AreaId(Long areaId);

   List<Meta> findByUsuario_AreaIdAndStatus_Id(Long areaId, Long statusId);

}
