package com.bianca.mobyMetas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.dto.EvidenciaCreateDTO;
import com.bianca.mobyMetas.dto.EvidenciaDetalhadaDTO;
import com.bianca.mobyMetas.model.Evidencia;
import com.bianca.mobyMetas.model.StatusMeta;
import com.bianca.mobyMetas.repository.EvidenciaRepository;
import com.bianca.mobyMetas.repository.MetaRepository;
import com.bianca.mobyMetas.repository.StatusMetaRepository;
import com.bianca.mobyMetas.model.Meta;
import java.util.Optional;

@RestController
@RequestMapping("/mobyMetas/v1/evidencias")
public class EvidenciaController {

    @Autowired
    private EvidenciaRepository evidenciaRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private StatusMetaRepository statusMetaRepository;

    @GetMapping
    public List<Evidencia> listarTodas() {
        return evidenciaRepository.findAll();
    }

    @GetMapping("/meta/{metaId}")
    public List<Evidencia> listarPorMeta(@PathVariable Long metaId) {
        return evidenciaRepository.findByMetaId(metaId);
    }

    @GetMapping("/detalhadas")
    public List<EvidenciaDetalhadaDTO> listarDetalhadas() {
        List<Object[]> resultados = evidenciaRepository.buscarEvidenciasDetalhadas();
        return resultados.stream().map(obj -> new EvidenciaDetalhadaDTO(
                ((Number) obj[0]).longValue(),
                (String) obj[1],
                ((java.sql.Timestamp) obj[2]).toLocalDateTime(),
                (String) obj[3],
                (String) obj[4])).toList();
    }

@PostMapping
public ResponseEntity<?> criarEvidencia(@RequestBody EvidenciaCreateDTO dto) {
    
    Optional<Evidencia> evidenciaExistente = evidenciaRepository.findFirstByMetaId(dto.getMetaId());

    if (evidenciaExistente.isPresent()) {
        
        return ResponseEntity.status(409).body("Já existe uma evidência para essa meta.");
    }

    Evidencia evidencia = new Evidencia();
    evidencia.setMetaId(dto.getMetaId());
    evidencia.setCaminhoImagem(dto.getCaminhoImagem());
    evidencia.setDataEnvio(LocalDateTime.now());

    evidencia = evidenciaRepository.save(evidencia);

    Meta meta = metaRepository.findById(dto.getMetaId())
            .orElseThrow(() -> new RuntimeException("Meta não encontrada"));

    if (meta.getStatus() != null && meta.getStatus().getNome().equalsIgnoreCase("pendente")) {
        StatusMeta statusConcluida = statusMetaRepository.findByNomeIgnoreCase("concluida")
                .orElseThrow(() -> new RuntimeException("Status 'concluida' não encontrado"));

        meta.setStatus(statusConcluida);
        metaRepository.save(meta);
    }

    return ResponseEntity.ok(evidencia);
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