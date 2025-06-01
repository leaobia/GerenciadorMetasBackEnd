package com.bianca.mobyMetas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.dto.EvidenciaCreateDTO;
import com.bianca.mobyMetas.dto.EvidenciaDetalhadaDTO;
import com.bianca.mobyMetas.model.Evidencia;
import com.bianca.mobyMetas.repository.EvidenciaRepository;

@RestController
@RequestMapping("/evidencias")
public class EvidenciaController {

    @Autowired
    private EvidenciaRepository evidenciaRepository;

    // GET /evidencias
    @GetMapping
    public List<Evidencia> listarTodas() {
        return evidenciaRepository.findAll();
    }

    // GET /evidencias/meta/{metaId}
    @GetMapping("/meta/{metaId}")
    public List<Evidencia> listarPorMeta(@PathVariable Long metaId) {
        return evidenciaRepository.findByMetaId(metaId);
    }

    // GET /evidencias/detalhadas
    @GetMapping("/detalhadas")
    public List<EvidenciaDetalhadaDTO> listarDetalhadas() {
        List<Object[]> resultados = evidenciaRepository.buscarEvidenciasDetalhadas();
        return resultados.stream().map(obj -> new EvidenciaDetalhadaDTO(
                ((Number) obj[0]).longValue(),
                (String) obj[1],
                ((java.sql.Timestamp) obj[2]).toLocalDateTime(),
                (String) obj[3],
                (String) obj[4]
        )).toList();
    }


    @PostMapping
    public Evidencia criarEvidencia(@RequestBody EvidenciaCreateDTO dto) {
        Evidencia evidencia = new Evidencia();
        evidencia.setMetaId(dto.getMetaId());
        evidencia.setCaminhoImagem(dto.getCaminhoImagem());
        evidencia.setDataEnvio(LocalDateTime.now());
        return evidenciaRepository.save(evidencia);
    }

  @PutMapping("/{id}")
public ResponseEntity<Evidencia> atualizarEvidencia(
        @PathVariable Long id,
        @RequestBody Evidencia novaEvidencia) {

    return evidenciaRepository.findById(id)
            .map(evidencia -> {
                evidencia.setMetaId(novaEvidencia.getMetaId());
                evidencia.setCaminhoImagem(novaEvidencia.getCaminhoImagem());
                evidencia.setDataEnvio(LocalDateTime.now()); 
                Evidencia atualizada = evidenciaRepository.save(evidencia);
                return ResponseEntity.ok(atualizada);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
}
}