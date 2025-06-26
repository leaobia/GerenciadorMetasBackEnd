package com.bianca.mobyMetas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.dto.ReportEvidenciaDTO;
import com.bianca.mobyMetas.model.ReportEvidencia;
import com.bianca.mobyMetas.model.Evidencia;
import com.bianca.mobyMetas.model.Meta;
import com.bianca.mobyMetas.model.StatusMeta;
import com.bianca.mobyMetas.repository.ReportEvidenciaRepository;
import com.bianca.mobyMetas.repository.EvidenciaRepository;
import com.bianca.mobyMetas.repository.MetaRepository;
import com.bianca.mobyMetas.repository.StatusMetaRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/mobyMetas/v1/report-evidencia")
public class ReportEvidenciaController {

    @Autowired
    private ReportEvidenciaRepository reportEvidenciaRepository;

    @Autowired
    private EvidenciaRepository evidenciaRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private StatusMetaRepository statusMetaRepository;

    @PostMapping
    public ResponseEntity<?> reportarEvidencia(@RequestBody ReportEvidenciaDTO dto) {
        ReportEvidencia report = new ReportEvidencia();
        report.setMetaId(dto.getMetaId());
        report.setUsuarioId(dto.getUsuarioId());
        report.setCaminhoImagem(dto.getCaminhoImagem());
        report.setMotivo(dto.getMotivo());
        report.setDataEnvio(LocalDateTime.now());
        report.setAnalisado(false);

        reportEvidenciaRepository.save(report);

        return ResponseEntity.ok("Evidência reportada para análise manual.");
    }

    @GetMapping("/pendentes")
    public List<ReportEvidencia> listarPendentes() {
        return reportEvidenciaRepository.findByAnalisadoFalse();
    }

    @PostMapping("/{id}/analisar")
    public ResponseEntity<?> analisarReport(@PathVariable Long id, @RequestParam boolean aprovado) {
        ReportEvidencia report = reportEvidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report não encontrado"));

        report.setAnalisado(true);
        report.setAprovado(aprovado);
        reportEvidenciaRepository.save(report);

        if (aprovado) {
            
            Evidencia evidencia = new Evidencia();
            evidencia.setMetaId(report.getMetaId());
            evidencia.setCaminhoImagem(report.getCaminhoImagem());
            evidencia.setDataEnvio(LocalDateTime.now());

            evidenciaRepository.save(evidencia);

            Meta meta = metaRepository.findById(report.getMetaId())
                    .orElseThrow(() -> new RuntimeException("Meta não encontrada"));

            StatusMeta statusConcluida = statusMetaRepository.findByNomeIgnoreCase("concluida")
                    .orElseThrow(() -> new RuntimeException("Status 'concluida' não encontrado"));

            meta.setStatus(statusConcluida);
            metaRepository.save(meta);
        }

        return ResponseEntity.ok("Análise concluída com sucesso.");
    }
}