package com.bianca.mobyMetas.controller;

import com.bianca.mobyMetas.dto.TipoMetaDTO;
import com.bianca.mobyMetas.service.TipoMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobyMetas/v1/tipo-metas")
public class TipoMetaController {

    @Autowired
    private TipoMetaService service;

    @GetMapping
    public ResponseEntity<List<TipoMetaDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMetaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}