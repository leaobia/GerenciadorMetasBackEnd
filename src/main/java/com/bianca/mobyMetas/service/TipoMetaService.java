package com.bianca.mobyMetas.service;

import com.bianca.mobyMetas.dto.TipoMetaDTO;
import com.bianca.mobyMetas.model.TipoMeta;
import com.bianca.mobyMetas.repository.TipoMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoMetaService {

    @Autowired
    private TipoMetaRepository repository;

    public List<TipoMetaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(tm -> new TipoMetaDTO(tm.getId(), tm.getNome()))
                .collect(Collectors.toList());
    }

    public TipoMetaDTO buscarPorId(Long id) {
        TipoMeta tm = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoMeta não encontrado"));
        return new TipoMetaDTO(tm.getId(), tm.getNome());
    }
}
