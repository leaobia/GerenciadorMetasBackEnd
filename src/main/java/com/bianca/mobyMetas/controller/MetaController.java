package com.bianca.mobyMetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bianca.mobyMetas.dto.MetaCreateDTO;
import com.bianca.mobyMetas.dto.MetaDTO;
import com.bianca.mobyMetas.model.Meta;
import com.bianca.mobyMetas.model.StatusMeta;
import com.bianca.mobyMetas.model.TipoMeta;
import com.bianca.mobyMetas.model.Usuario;
import com.bianca.mobyMetas.repository.MetaRepository;

import java.util.List;

@RestController
@RequestMapping("/mobyMetas/v1/metas")
public class MetaController {

    private final MetaRepository metaRepository;

    public MetaController(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @GetMapping
    public List<Meta> listarMetas() {
        return metaRepository.findAll();
    }

    @GetMapping("/pendentes")
    public List<Meta> listarMetasPendentes() {
        return metaRepository.findByStatusNome("pendente");
    }

    @GetMapping("/concluidas")
    public List<Meta> listarMetasConcluidas() {
        return metaRepository.findByStatusNome("concluida");
    }

    @GetMapping("/usuario/{id}")
    public List<Meta> listarMetasPorUsuario(@PathVariable Long id) {
        return metaRepository.findByUsuarioId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaDTO> getMetaPorId(@PathVariable Long id) {
        return metaRepository.findById(id)
                .map(meta -> {
                    MetaDTO dto = new MetaDTO();
                    dto.setId(meta.getId());
                    dto.setTitulo(meta.getTitulo());
                    dto.setDescricao(meta.getDescricao());
                    dto.setUsuario(meta.getUsuario().getNome());
                    dto.setStatus(meta.getStatus().getNome());
                    dto.setTipoId(meta.getTipoMeta() != null ? meta.getTipoMeta().getId() : null);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/area/{areaId}")
    public List<MetaDTO> getMetasPorArea(@PathVariable Long areaId) {
        List<Meta> metas = metaRepository.findByUsuario_AreaId(areaId);

        return metas.stream().map(meta -> {
            MetaDTO dto = new MetaDTO();
            dto.setId(meta.getId());
            dto.setTitulo(meta.getTitulo());
            dto.setDescricao(meta.getDescricao());
            dto.setUsuario(meta.getUsuario().getNome());
            dto.setStatus(meta.getStatus().getNome());
            dto.setTipoId(meta.getTipoMeta() != null ? meta.getTipoMeta().getId() : null);
            return dto;
        }).toList();
    }

    @GetMapping("/area/{areaId}/status/{statusId}")
    public List<MetaDTO> getMetasPorAreaEStatus(@PathVariable Long areaId, @PathVariable Long statusId) {
        List<Meta> metas = metaRepository.findByUsuario_AreaIdAndStatus_Id(areaId, statusId);

        return metas.stream().map(meta -> {
            MetaDTO dto = new MetaDTO();
            dto.setId(meta.getId());
            dto.setTitulo(meta.getTitulo());
            dto.setDescricao(meta.getDescricao());
            dto.setUsuario(meta.getUsuario().getNome());
            dto.setStatus(meta.getStatus().getNome());
            dto.setTipoId(meta.getTipoMeta() != null ? meta.getTipoMeta().getId() : null);
            return dto;
        }).toList();
    }

    @PostMapping
    public Meta criarMeta(@RequestBody MetaCreateDTO dto) {
        System.out.println("Recebido DTO: " + dto);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());

        StatusMeta status = new StatusMeta();
        status.setId(dto.getStatusId());

        TipoMeta tipoMeta = new TipoMeta();
        tipoMeta.setId(dto.getTipoId());

        Meta meta = new Meta();
        meta.setTitulo(dto.getTitulo());
        meta.setDescricao(dto.getDescricao());
        meta.setUsuario(usuario);
        meta.setStatus(status);
        meta.setTipoMeta(tipoMeta);

        return metaRepository.save(meta);
    }

}