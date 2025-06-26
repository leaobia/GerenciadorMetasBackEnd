package com.bianca.mobyMetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.bianca.mobyMetas.model.ReportEvidencia;

public interface ReportEvidenciaRepository extends JpaRepository<ReportEvidencia, Long> {

    List<ReportEvidencia> findByAnalisadoFalse();

}