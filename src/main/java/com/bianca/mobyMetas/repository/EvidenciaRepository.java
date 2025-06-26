package com.bianca.mobyMetas.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bianca.mobyMetas.model.Evidencia;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    List<Evidencia> findByMetaId(Long metaId);

    Optional<Evidencia> findFirstByMetaId(Long metaId);  

    @Query(value = """
        SELECT 
            e.id, 
            e.caminho_imagem, 
            e.data_envio, 
            m.titulo AS meta_titulo, 
            u.nome AS usuario 
        FROM evidencias e
        JOIN metas m ON e.meta_id = m.id
        JOIN usuarios u ON m.usuario_id = u.id
    """, nativeQuery = true)
    List<Object[]> buscarEvidenciasDetalhadas();
}